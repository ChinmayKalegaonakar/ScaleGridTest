
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kaleg
 */
public class EmailTask implements Runnable {
    
    private String recipient;
    private String contactName;
    private String contactEmail;
    private String body;

    public EmailTask(String recipient, String contactName, String contactEmail) {
        this.recipient = recipient;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.body = "You contact "+contactName+" has their birthday soon , wish them at "+contactEmail;
    }
    


    @Override
    public void run() {
        try {
            MailHelper.sendEmail("ScaleGridApp@gmail.com", recipient, "Upcoming Birthdays", body);
            System.out.println("email to " + recipient +" is sent");
        } catch (EmailException ex) {
            Logger.getLogger(EmailTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
