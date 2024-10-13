package com.pucmg.lab03.Services;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucmg.lab03.Models.Aluno;
import com.pucmg.lab03.Models.Empresa;
import com.pucmg.lab03.Models.Professor;
import com.pucmg.lab03.Models.Transacao;
import com.pucmg.lab03.Models.Usuario;
import com.pucmg.lab03.Repositories.TransacaoRepository;
import com.pucmg.lab03.Repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

public class TransacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    public void transferirMoedas(Usuario remetente, Usuario destinatario, int valor, String motivo) {
        Transacao transacao = new Transacao();

        remetente = usuarioRepository.findById(remetente.getId()).get();
        destinatario = usuarioRepository.findById(destinatario.getId()).get();

        if (remetente == null || destinatario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        if (remetente == destinatario) {
            throw new RuntimeException("Não é possível transferir moedas para você mesmo");
        }

        if (valor <= 0) {
            throw new RuntimeException("Valor inválido");
        }

        if (remetente instanceof Professor || remetente instanceof Aluno &&
            destinatario instanceof Aluno || destinatario instanceof Empresa) {

            int saldoRemetente;

            // Verifica e realiza o cast para obter o saldo do remetente
            if (remetente instanceof Professor) {
                saldoRemetente = ((Professor) remetente).getSaldoMoedas();
            } else if (remetente instanceof Aluno) {
                saldoRemetente = ((Aluno) remetente).getSaldoMoedas();
            } else {
                throw new RuntimeException("Tipo de remetente inválido");
            }
            transacao.setRemetente(remetente);
            transacao.setDestinatario(destinatario);
            transacao.setMontante(valor);
            transacao.setMotivo(motivo);
            salvarTransacao(transacao);

            // Verifica se o saldo é suficiente para a transação
            if (saldoRemetente >= valor) {
                // Atualiza o saldo do remetente
                if (remetente instanceof Professor) {
                    ((Professor) remetente).setSaldoMoedas(saldoRemetente - valor);
                    professorService.salvarProfessor((Professor) remetente);
                } else if (remetente instanceof Aluno) {
                    ((Aluno) remetente).setSaldoMoedas(saldoRemetente - valor);
                    alunoService.salvarAluno((Aluno) remetente);
                }

                // Atualiza o saldo do destinatário se for aluno, já que empresa não tem saldo
                if (destinatario instanceof Aluno) {
                    ((Aluno) destinatario).setSaldoMoedas(((Aluno) destinatario).getSaldoMoedas() + valor);
                    alunoService.salvarAluno((Aluno) remetente);
                }

            } else {
                throw new RuntimeException("Saldo insuficiente");
            }
        }

    }

    @Transactional
    public Transacao salvarTransacao(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

}
