package com.pucmg.lab03.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;

import com.pucmg.lab03.Models.Aluno;
import com.pucmg.lab03.Services.AlunoService;
import com.pucmg.lab03.dto.RankingResponseDTO;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    AlunoService alunoService;

    @GetMapping("/all")
    public ResponseEntity<List<RankingResponseDTO>> obterRanking() {
        List<Aluno> alunosOrdenados = alunoService.melhoresAlunos();

        List<RankingResponseDTO> ranking = new ArrayList<>();
        int[] posicaoRanking = { 1 };
        alunosOrdenados.forEach(aluno -> {
            ranking.add(new RankingResponseDTO(
                    posicaoRanking[0]++,
                    aluno.getNome(),
                    aluno.getCurso(),
                    aluno.getTotalMoedasRecebidas()));
        });

        return ResponseEntity.ok(ranking);
    }

}
