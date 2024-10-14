package com.pucmg.lab03.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pucmg.lab03.Models.Aluno;

import jakarta.mail.MessagingException;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AlunoService alunoService;

        // testes
//     public void enviarNotificacaoRecebimentoSimples(String para) throws MessagingException {
//     SimpleMailMessage email = new SimpleMailMessage();
//     email.setTo(para);
//     email.setSubject("Teste de Autenticação");
//     email.setText("Este é um teste de e-mail simples para verificar a autenticação.");

//     mailSender.send(email);
// }


    public void enviarNotificacaoRecebimentoMoedas(String para, int quantidadeMoedas) throws MessagingException {
        Aluno aluno = alunoService.buscarAlunoPorEmail(para);
        String assunto = "Recebimento de Aracoins!";
        String mensagemHtml = "<html><body>" +
                "<h3>Olá, " + aluno.getNome() + "!</h3>" +
                "<p>Você recebeu <b>" + quantidadeMoedas + " Aracoins!</b></p>" +
                "<img src='cid:aracoin'>" +  // Referência à imagem embutida
                "</body></html>";

        // Cria uma mensagem MIME
        jakarta.mail.internet.MimeMessage email = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(email, true); // `true` para conteúdo HTML e anexos
        helper.setFrom("no-reply@demomailtrap.com");
        helper.setTo(para);
        helper.setSubject(assunto);
        helper.setText(mensagemHtml, true); // `true` para HTML

        // Adiciona a imagem embutida (exemplo com imagem em resources)
        ClassPathResource image = new ClassPathResource("static/images/aracoin.png"); // ajuste o caminho conforme necessário
        helper.addInline("aracoin", image); // "logoImage" deve coincidir com a referência "cid:logoImage" no HTML

        // Envia o e-mail
        mailSender.send(email);
    }
}
