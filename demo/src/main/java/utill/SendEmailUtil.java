package utill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailUtil{

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 用来发送模版邮件
     */
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    public void send(String to, String subject, String text) {

    //    SimpleMailMessage message = new SimpleMailMessage();
    //    message.setFrom(from);
    //    message.setTo(to);
    //    message.setSubject(subject);
    //    message.setText(text);

        Context context = new Context();
        context.setVariable("project", "demo");
        context.setVariable("author", "yimcarson");
        context.setVariable("code", "text");
        String emailContent = templateEngine.process("mail", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(emailContent, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
}