package com.pucmg.lab03.Services;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

import com.pucmg.lab03.Models.Aluno;
import com.pucmg.lab03.Models.Professor;
import com.pucmg.lab03.Models.Transacao;
import com.pucmg.lab03.Models.Usuario;
import com.pucmg.lab03.Models.Vantagem;
import com.pucmg.lab03.Repositories.TransacaoRepository;
import com.pucmg.lab03.Repositories.UsuarioRepository;
import java.util.List;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

@Service
public class TransacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private VantagemService vantagemService;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void professorTransferirMoedas(Usuario remetente, Usuario destinatario, int valor, String detalhes) {
        Transacao transacao = new Transacao();

        remetente = usuarioRepository.findById(remetente.getId())
                .orElseThrow(() -> new RuntimeException("Remetente não encontrado"));
        destinatario = usuarioRepository.findById(destinatario.getId())
                .orElseThrow(() -> new RuntimeException("Destinatário não encontrado"));

        if (remetente.equals(destinatario)) {
            throw new RuntimeException("Não é possível transferir moedas para você mesmo");
        }

        if (valor <= 0) {
            throw new RuntimeException("Valor inválido");
        }

        // Verificação correta dos tipos de remetente e destinatário
        if ((remetente instanceof Professor && destinatario instanceof Aluno)) {

            int saldoRemetente = ((Professor) remetente).getSaldoMoedas();

            // Verifica se o saldo é suficiente para a transação
            if (saldoRemetente >= valor) {
                ((Professor) remetente).setSaldoMoedas(saldoRemetente - valor);
                professorService.salvarProfessor((Professor) remetente);

                ((Aluno) destinatario).setSaldoMoedas(((Aluno) destinatario).getSaldoMoedas() + valor);
                ((Aluno) destinatario).setTotalMoedasRecebidas(((Aluno) destinatario).getTotalMoedasRecebidas() + valor);
                alunoService.salvarAluno((Aluno) destinatario);
                // salva o historico da transacao
                transacao.setRemetente(remetente);
                transacao.setDestinatario(destinatario);
                transacao.setMontante(valor);
                transacao.setDetalhes(detalhes);
                transacao.setData(LocalDate.now());
                salvarTransacao(transacao); 
                // envia email de notificacao para o aluno
                try {
                    emailService.enviarNotificacaoRecebimentoMoedas(((Aluno) destinatario).getEmail(), valor);
                } catch (MessagingException e) {
                    throw new RuntimeException("Falha ao enviar notificação por email", e);
                }
            } else {
                throw new RuntimeException("Saldo insuficiente");
            }
        } else {
            throw new RuntimeException("Tipos de remetente ou destinatário inválidos para a transação");
        }
    }

    @Transactional
    public void alunoComprarVantagem(Long alunoId, Long vantagemId) {

        Aluno aluno = alunoService.buscarAluno(alunoId);
        Vantagem vantagem = vantagemService.buscarVantagem(vantagemId);
        Transacao transacao = new Transacao();

        if (aluno.getSaldoMoedas() >= vantagem.getPreco()) {
            aluno.setSaldoMoedas(aluno.getSaldoMoedas() - vantagem.getPreco());
            alunoService.salvarAluno(aluno);
            transacao.setMontante(vantagem.getPreco());
            transacao.setDetalhes(vantagem.getDetalhes());
            transacao.setData(LocalDate.now());
            transacao.setDestinatario(vantagem.getEmpresa());
            transacao.setRemetente(aluno);
            salvarTransacao(transacao);
        } 
        
        else {
            throw new RuntimeException("Saldo insuficiente");
        }
        // falta criar e chamar o método do cupom ir para o email do aluno
    }

    public List<Transacao> buscarTransacoesEnviadas(Long remetenteId) {
        return transacaoRepository.findByRemetenteId(remetenteId);
    }

    public List<Transacao> buscarTransacoesRecebidas(Long destinatarioId) {
        return transacaoRepository.findByDestinatarioId(destinatarioId);
    }

    @Transactional
    public Transacao salvarTransacao(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }
}
