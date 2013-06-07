package iopiotrsukiennik.whowhen.backend.service;

import iopiotrsukiennik.whowhen.shared.form.RequestData;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 02.12.12
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public class RequestProgressMailNotifier {

    private JavaMailSender mailSender;
    private String subject;
    private String from;
    private VelocityEngine velocityEngine;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
    public void sendCompletionNotification(final String requestIdentifier, final RequestData request) {
        try {
            MimeMessage message =  mailSender.createMimeMessage();
            Map model = new HashMap();
            model.put("requestIdentifier",requestIdentifier);
            message.setSubject(subject);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(request.getEmail()));
            String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/mail_requestCompleted.vm", model);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(text, "text/html");
            MimeMultipart multipart = new MimeMultipart("related");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            this.mailSender.send(message);
        }
        catch (MailException ex) {
            ex.printStackTrace();
        }  catch (MessagingException messagingException){
           messagingException.printStackTrace();
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
