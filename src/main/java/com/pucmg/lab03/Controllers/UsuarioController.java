package com.pucmg.lab03.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pucmg.lab03.Models.Aluno;
import com.pucmg.lab03.Models.Empresa;
import com.pucmg.lab03.Models.InstituicaoEnsino;
import com.pucmg.lab03.Models.Professor;
import com.pucmg.lab03.Models.Usuario;
import com.pucmg.lab03.Services.AlunoService;
import com.pucmg.lab03.Services.AuthService;
import com.pucmg.lab03.Services.EmpresaService;
import com.pucmg.lab03.Services.InstituicaoEnsinoService;
import com.pucmg.lab03.Services.ProfessorService;
import com.pucmg.lab03.Services.UsuarioService;
import com.pucmg.lab03.dto.AlunoProfessorResponseHeaderDTO;
import com.pucmg.lab03.dto.EmpresaInstituicaoFotoResponseDTO;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthService authService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private InstituicaoEnsinoService instituicaoService;

    @Operation(summary = "Obter o header do usuario de acordo com seu tipo")
@GetMapping("/header/professor_aluno")
public ResponseEntity<Object> buscarUsuarioHeader(@RequestParam Long id) {
    try {
        Usuario usuario = usuarioService.buscarUsuario(id);
        if (authService.determinarTipoUsuario(usuario).equals("Professor")) {
            Professor professor = professorService.buscarProfessor(id);
            AlunoProfessorResponseHeaderDTO professorHeader = new AlunoProfessorResponseHeaderDTO(
                    professor.getSaldoMoedas(), professor.getFotoPerfil());
            return ResponseEntity.ok(professorHeader);
        } else if (authService.determinarTipoUsuario(usuario).equals("Aluno")) {
            Aluno aluno = alunoService.buscarAluno(id);
            AlunoProfessorResponseHeaderDTO alunoHeader = new AlunoProfessorResponseHeaderDTO(
                    aluno.getSaldoMoedas(), aluno.getFotoPerfil());
            return ResponseEntity.ok(alunoHeader);
        } else if (authService.determinarTipoUsuario(usuario).equals("Empresa")) {
            Empresa empresa = empresaService.buscarEmpresa(id);
            EmpresaInstituicaoFotoResponseDTO empresaHeader = new EmpresaInstituicaoFotoResponseDTO(
                    empresa.getFotoPerfil());
            return ResponseEntity.ok(empresaHeader);
        } else {
            InstituicaoEnsino instituicao = instituicaoService.buscarInstituicaoEnsino(id);
            EmpresaInstituicaoFotoResponseDTO instituicaoHeader = new EmpresaInstituicaoFotoResponseDTO(
                    instituicao.getFotoPerfil());
            return ResponseEntity.ok(instituicaoHeader);
        }
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}

    

}
