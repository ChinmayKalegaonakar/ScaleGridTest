
import controllers.Security;
import models.Contact;
import models.User;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import static play.data.validation.Validation.email;
import play.libs.Mail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kaleg
 */
public class MailHelper {
    
    
    public static void sendEmail(String from , String to, String subject , String message ) throws EmailException{
            SimpleEmail email = new SimpleEmail();
            email.setFrom(from);
            email.addTo(to);
            email.setSubject(subject);
            email.setMsg(message);
            Mail.send(email); 
    }
}
