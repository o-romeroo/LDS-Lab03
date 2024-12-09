package com.pucmg.lab03.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

import com.pucmg.lab03.Models.Usuario;
import com.pucmg.lab03.Repositories.UsuarioRepository;
import com.pucmg.lab03.Services.TransacaoService;
import com.pucmg.lab03.dto.ExtratoProfessorResponseDTO;
import com.pucmg.lab03.dto.ExtratoTodosAlunoResponseDTO;
import com.pucmg.lab03.dto.TransferenciaRequestDTO;
import com.pucmg.lab03.dto.ComprarVantagemRequestDTO;
import com.pucmg.lab03.dto.ExtratoAlunoResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

        @Autowired
        private TransacaoService transacaoService;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Operation(summary = "Transfere moedas de um professor para aluno", description = "<b>remetenteId</b> é o <b>ID</b> do professor que está transferindo as moedas.<br>"
                        +
                        "<b>destinatarioId</b> é o <b>ID</b> do aluno que está recebendo as moedas.<br><b>valor</b> é a quantidade de moedas a ser transferida.<br><b>motivo</b>"
                        + "é a justificativa da transferência.")
        @PostMapping("professor/transferencia")
        public ResponseEntity<String> transferirMoedas(@RequestBody TransferenciaRequestDTO transacaoRequest) {

                // Busca os usuários pelo ID
                Usuario remetente = usuarioRepository.findById(transacaoRequest.getRemetenteId())
                                .orElseThrow(() -> new RuntimeException("Remetente não encontrado"));
                Usuario destinatario = usuarioRepository.findById(transacaoRequest.getDestinatarioId())
                                .orElseThrow(() -> new RuntimeException("Destinatário não encontrado"));

                        transacaoService.professorTransferirMoedas(remetente, destinatario, transacaoRequest.getValor(),
                                        transacaoRequest.getMotivo());
                        return ResponseEntity.ok("Transferência realizada com sucesso!");
        }

        @Operation(summary = "Lista de todas transacoes enviadas por um professor", description = "{usuarioId} neste caso é o ID do professor")
        @GetMapping("professor/enviadas/{usuarioId}")
        public ResponseEntity<List<ExtratoProfessorResponseDTO>> buscarTransacoesEnviadasProfessor(
                        @PathVariable Long usuarioId) {
                List<ExtratoProfessorResponseDTO> transacoesEnviadasDto = transacaoService
                                .buscarTransacoesEnviadas(usuarioId)
                                .stream()
                                .map(extrato -> new ExtratoProfessorResponseDTO(
                                                extrato.getFotoRemetente(),
                                                extrato.getFotoDestinatario(),
                                                extrato.getMontante(),
                                                extrato.getDestinatario().getNome(),
                                                extrato.getDetalhes(),
                                                extrato.getData()))
                                .collect(Collectors.toList());

                return ResponseEntity.ok(transacoesEnviadasDto);
        }

        @Operation(summary = "Lista de todas transacoes RECEBIDAS por um aluno", description = "{usuarioId} neste caso é o ID do <b>aluno</b>")
        @GetMapping("aluno/recebidas/{usuarioId}")
        public ResponseEntity<List<ExtratoAlunoResponseDTO>> buscarTransacoesRecebidasAluno(
                        @PathVariable Long usuarioId) {
                List<ExtratoAlunoResponseDTO> transacoesRecebidasDto = transacaoService
                                .buscarTransacoesRecebidas(usuarioId)
                                .stream()
                                .map(extrato -> new ExtratoAlunoResponseDTO(
                                                extrato.getMontante(),
                                                extrato.getRemetente().getNome(),
                                                extrato.getDetalhes(),
                                                extrato.getData(),
                                                extrato.getRemetente().getFotoPerfil())) // pega a imagem do professor
                                                                                         // que enviou as moedas
                                .collect(Collectors.toList());

                return ResponseEntity.ok(transacoesRecebidasDto);
        }

        // precisa retornar a imagem
        @Operation(summary = "Lista de todas transacoes ENVIADAS por um aluno", description = "{usuarioId} neste caso é o ID do <b>aluno</b>")
        @GetMapping("aluno/enviadas/{usuarioId}")
        public ResponseEntity<List<ExtratoAlunoResponseDTO>> buscarTransacoesEnviadasAluno(
                        @PathVariable Long usuarioId) {
                List<ExtratoAlunoResponseDTO> transacoesEnviadasDto = transacaoService
                                .buscarTransacoesEnviadas(usuarioId)
                                .stream()
                                .map(extrato -> new ExtratoAlunoResponseDTO(
                                                extrato.getMontante(),
                                                extrato.getDestinatario().getNome(),
                                                extrato.getDetalhes(),
                                                extrato.getData(),
                                                extrato.getDestinatario().getFotoPerfil())) // pega a imagem da
                                                                                            // empresa/instuição que
                                                                                            // recebeu as moedas
                                .collect(Collectors.toList());

                return ResponseEntity.ok(transacoesEnviadasDto);
        }

        @Operation(summary = "Lista de TODAS transacoes de um aluno, tanto recebidas quanto enviadas", description = "{usuarioId} neste caso é o ID do <b>aluno</b><br> este método faz parte da página Extrato do Aluno")
        @GetMapping("aluno/todas/{usuarioId}")
        public ResponseEntity<List<ExtratoTodosAlunoResponseDTO>> buscarTodasTransacoes(@PathVariable Long usuarioId) {
                List<ExtratoTodosAlunoResponseDTO> transacoesDto = transacaoService.buscarTodasTransacoes(usuarioId)
                                .stream()
                                .map(extrato -> new ExtratoTodosAlunoResponseDTO(
                                                extrato.getMontante(),
                                                extrato.getRemetente().getId().equals(usuarioId)
                                                                ? extrato.getDestinatario().getNome()
                                                                : extrato.getRemetente().getNome(),
                                                extrato.getDetalhes(),
                                                extrato.getData(),
                                                extrato.getRemetente().getId().equals(usuarioId)
                                                                ? extrato.getDestinatario().getFotoPerfil()
                                                                : extrato.getRemetente().getFotoPerfil(),
                                                extrato.getRemetente().getId().equals(usuarioId) ? "saida"
                                                                : "entrada" // Define o tipo de transação
                                ))
                                .collect(Collectors.toList());

                return ResponseEntity.ok(transacoesDto);
        }

        @Operation(summary = "Compra de vantagem por um aluno", description = "Debita moedas de um aluno para comprar uma vantagem.")
        @PostMapping("aluno/compra")
        public ResponseEntity<String> comprarVantagem(@RequestBody ComprarVantagemRequestDTO request) {
                        transacaoService.alunoComprarVantagem(request.getAlunoId(), request.getVantagemId());
                        return ResponseEntity.ok("Vantagem comprada com sucesso!");

        }

}
