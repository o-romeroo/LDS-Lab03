package com.pucmg.lab03.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.Random;
import com.pucmg.lab03.Models.Aluno;
import com.pucmg.lab03.Models.Professor;
import com.pucmg.lab03.Models.Usuario;
import com.pucmg.lab03.Models.Vantagem;

import jakarta.mail.MessagingException;

@Service
public class EmailService {

    private int codigoCupom;

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
                "<div style='max-width: 600px; margin: auto; border: 1px solid #ddd; border-radius: 10px; padding: 20px; background-color: #f9f9f9;'>"
                +
                "<div style='text-align: center;'>" +
                "<p style='font-size: 18px; color: #555;'>" + professor.getNome() + " enviou para você:</p>" +
                "<img src='cid:fotoProfessor' style='width: 80px; height: 80px; border-radius: 50%; margin-bottom: 10px;'>"
                +
                "</div>" +
                "<hr style='border: none; border-top: 1px solid #ddd; margin: 20px 0;'>" +
                "<div style='text-align: center;'>" +
                "<h2 style='color: #4CAF50;'>Olá, " + aluno.getNome() + "!</h2>" +
                "<p style='font-size: 20px; font-weight: bold; color: #333; margin-bottom: 15px;'>Você recebeu moedas Aracoins!</p>"
                +
                "<div style='text-align: center; margin: 15px 0;'>" +
                "<img src='cid:aracoin' style='width: 60px; height: 60px; margin-right: 0px; vertical-align: middle;'>"
                + // Reduz a margem direita para aproximar o texto
                "<p style='font-size: 24px; color: #4CAF50; font-weight: bold; display: inline; margin: 0; vertical-align: middle;'>"
                + quantidadeMoedas + " Aracoins</p>" +
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

    public void enviarCupomDescontoAluno(String para, String descricaoVantagem, Usuario usuario, Vantagem vantagem)
            throws MessagingException {
        Random random = new Random();
        this.codigoCupom = 1000 + random.nextInt(9999);
        String assunto = "Cupom de Desconto!";

        Aluno aluno = alunoService.buscarAlunoPorEmail(para);

        String mensagemHtml = "<html><body style='font-family: Arial, sans-serif; color: #333;'>" +
                "<div style='max-width: 600px; margin: auto; border: 1px solid #ddd; border-radius: 10px; padding: 20px; background-color: #f9f9f9;'>"
                +
                "<div style='text-align: center;'>" +
                "<p style='font-size: 18px; color: #555;'>" + usuario.getNome()
                + " oferece um desconto exclusivo para você!</p>" +
                "<img src='cid:fotoUsuario' style='width: 80px; height: 80px; border-radius: 50%; margin-bottom: 10px;'>"
                +
                "</div>" +
                "<hr style='border: none; border-top: 1px solid #ddd; margin: 20px 0;'>" +
                "<div style='text-align: center;'>" +
                "<h2 style='color: #4CAF50;'>Olá, " + aluno.getNome() + "!</h2>" +
                "<p style='font-size: 20px; font-weight: bold; color: #333; margin-bottom: 15px;'>Você resgatou um cupom de desconto!</p>"
                +
                "<div style='text-align: center; margin: 15px 0;'>" +
                "<img src='cid:imagemVantagem' style='width: 100px; height: 80px; margin-bottom: 10px; vertical-align: middle;'>"
                +
                "<p style='font-size: 16px; color: #333; font-weight: bold; margin-top: 10px;'>" + descricaoVantagem
                + "</p>" +
                "</div>" +
                "<p style='font-size: 16px; margin-top: 10px; color: #333;'>Utilize o seguinte código presencialmente para resgatar sua vantagem.</p>"
                +
                "<p style='font-size: 24px; color: #4CAF50; font-weight: bold; display: inline; margin: 0;'>"
                + codigoCupom + "</p>" +
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

        // Embute a imagem do usuário como "cid:fotoUsuario"
        byte[] imgUsuario = usuario.getFotoPerfil();
        if (imgUsuario != null) {
            helper.addInline("fotoUsuario", new ByteArrayResource(imgUsuario), "image/png");
        }

        // Embute a imagem da vantagem como "cid:imagemVantagem"
        byte[] imgVantagem = vantagem.getImagem();
        if (imgVantagem != null) {
            helper.addInline("imagemVantagem", new ByteArrayResource(imgVantagem), "image/png");
        }

        // Envia o e-mail
        mailSender.send(email);
    }

    public void enviarConfirmacaoCompraEmpresa(String para, String descricaoVantagem, Aluno aluno, Vantagem vantagem)
            throws MessagingException {
        
        String assunto = "Confirmação de Resgate de Cupom!";

        String mensagemHtml = "<html><body style='font-family: Arial, sans-serif; color: #333;'>" +
                "<div style='max-width: 600px; margin: auto; border: 1px solid #ddd; border-radius: 10px; padding: 20px; background-color: #f9f9f9;'>"
                +
                "<div style='text-align: center;'>" +
                "<p style='font-size: 18px; color: #555;'>Confirmação de Resgate de Cupom</p>" +
                "<p style='font-size: 16px; color: #333;'>O aluno " + aluno.getNome()
                + " resgatou um cupom para a sua vantagem:</p>" +
                "<img src='cid:fotoAluno' style='width: 80px; height: 80px; border-radius: 50%; margin-bottom: 10px;'>"
                +
                "</div>" +
                "<hr style='border: none; border-top: 1px solid #ddd; margin: 20px 0;'>" +
                "<div style='text-align: center;'>" +
                "<h2 style='color: #4CAF50;'>Detalhes do Resgate</h2>" +
                "<div style='text-align: center; margin: 15px 0;'>" +
                "<img src='cid:imagemVantagem' style='width: 100px; height: 80px; margin-bottom: 10px; vertical-align: middle;'>"
                +
                "<p style='font-size: 16px; color: #333; font-weight: bold; margin-top: 10px;'>" + descricaoVantagem
                + "</p>" +
                "</div>" +
                "<p style='font-size: 16px; margin-top: 10px; color: #333;'>Código do Cupom Resgatado:</p>" +
                "<p style='font-size: 24px; color: #4CAF50; font-weight: bold; display: inline; margin: 0;'>"
                + codigoCupom + "</p>" +
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

        // Embute a imagem do aluno como "cid:fotoAluno"
        byte[] imgAluno = aluno.getFotoPerfil();
        if (imgAluno != null) {
            helper.addInline("fotoAluno", new ByteArrayResource(imgAluno), "image/png");
        }

        // Embute a imagem da vantagem como "cid:imagemVantagem"
        byte[] imgVantagem = vantagem.getImagem();
        if (imgVantagem != null) {
            helper.addInline("imagemVantagem", new ByteArrayResource(imgVantagem), "image/png");
        }

        // Envia o e-mail
        mailSender.send(email);
    }

}
