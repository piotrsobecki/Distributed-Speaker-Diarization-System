package iopiotrsukiennik.whowhen.backend.service.event;

import iopiotrsukiennik.whowhen.shared.event.ProgressListener;
import iopiotrsukiennik.whowhen.shared.form.RequestData;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Piotr Sukiennik
 */
public class MailSenderProgressListener implements ProgressListener {

    private JavaMailSender mailSender;

    private String subject;

    private String from;

    private VelocityEngine velocityEngine;

    public void setMailSender( JavaMailSender mailSender ) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine( VelocityEngine velocityEngine ) {
        this.velocityEngine = velocityEngine;
    }

    @Override
    public void notify( int progress, String requestIdentifier ) {

    }

    public void notify( int progress, final String requestIdentifier, final RequestData request ) {
        try {
            if ( progress == 100 && request != null && request.getEmail() != null && !request.getEmail().isEmpty() ) {
                MimeMessage message = mailSender.createMimeMessage();
                Map model = new HashMap();
                model.put( "requestIdentifier", requestIdentifier );
                message.setSubject( subject );
                message.setFrom( new InternetAddress( from ) );
                message.addRecipient( Message.RecipientType.TO, new InternetAddress( request.getEmail() ) );
                String text = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "velocity/mail_requestCompleted.vm", model );
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent( text, "text/html" );
                MimeMultipart multipart = new MimeMultipart( "related" );
                multipart.addBodyPart( messageBodyPart );
                message.setContent( multipart );
                this.mailSender.send( message );
            }
        }
        catch ( MailException ex ) {
            ex.printStackTrace();
        }
        catch ( MessagingException messagingException ) {
            messagingException.printStackTrace();
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject( String subject ) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom( String from ) {
        this.from = from;
    }
}
