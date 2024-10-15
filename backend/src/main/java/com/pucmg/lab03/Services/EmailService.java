package com.pucmg.lab03.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pucmg.lab03.Models.Aluno;
import com.pucmg.lab03.Models.Professor;

import jakarta.mail.MessagingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AlunoService alunoService;

    // testes
    // public void enviarNotificacaoRecebimentoSimples(String para) throws
    // MessagingException {
    // SimpleMailMessage email = new SimpleMailMessage();
    // email.setTo(para);
    // email.setSubject("Teste de Autenticação");
    // email.setText("Este é um teste de e-mail simples para verificar a
    // autenticação.");

    // mailSender.send(email);
    // }

    public void enviarNotificacaoRecebimentoMoedas(String para, int quantidadeMoedas, String detalhes,
            Professor professor)
            throws MessagingException {
        Aluno aluno = alunoService.buscarAlunoPorEmail(para);
        String assunto = "Recebimento de Aracoins!";

        // Criação do HTML com referências "cid" para as imagens
        String mensagemHtml = "<html><body style='font-family: Arial, sans-serif; color: #333;'>" +
            "<div style='max-width: 600px; margin: auto; border: 1px solid #ddd; border-radius: 10px; padding: 20px; background-color: #f9f9f9;'>" +
                "<div style='text-align: center;'>" +
                    "<p style='font-size: 18px; color: #555;'>" + professor.getNome() + " enviou para você:</p>" +
                    "<img src='cid:fotoProfessor' style='width: 80px; height: 80px; border-radius: 50%; margin-bottom: 10px;'>" +
                "</div>" +
                "<hr style='border: none; border-top: 1px solid #ddd; margin: 20px 0;'>" +
                "<div style='text-align: center;'>" +
                    "<h2 style='color: #4CAF50;'>Olá, " + aluno.getNome() + "!</h2>" +
                    "<p style='font-size: 20px; font-weight: bold; color: #333; margin-bottom: 15px;'>Você recebeu moedas Aracoins!</p>" +
                    "<div style='text-align: center; margin: 15px 0;'>" +
                        "<img src='cid:aracoin' style='width: 60px; height: 60px; margin-right: 0px; vertical-align: middle;'>" + // Reduz a margem direita para aproximar o texto
                        "<p style='font-size: 24px; color: #4CAF50; font-weight: bold; display: inline; margin: 0; vertical-align: middle;'>" + quantidadeMoedas + " Aracoins</p>" +
                    "</div>" +
                    "<p style='font-size: 16px; margin-top: 10px;'>Detalhes: " + detalhes + "</p>" +
                "</div>" +
            "</div>" +
            "</body></html>";

        // Cria uma mensagem MIME
        jakarta.mail.internet.MimeMessage email = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(email, true); // `true` para conteúdo HTML e anexos
        helper.setFrom("no-reply@demomailtrap.com");
        helper.setTo(para);
        helper.setSubject(assunto);
        helper.setText(mensagemHtml, true); // `true` para HTML

        // Embute a imagem do professor como "cid:fotoProfessor"
        byte[] imgProfessor = professor.getFotoPerfil();
        if (imgProfessor != null) {
            helper.addInline("fotoProfessor", new ByteArrayResource(imgProfessor), "image/png");
        }

        // Adiciona a imagem "aracoin" embutida
        ClassPathResource image = new ClassPathResource("static/images/aracoin.png");
        helper.addInline("aracoin", image);

        // Envia o e-mail
        mailSender.send(email);
    }

}
