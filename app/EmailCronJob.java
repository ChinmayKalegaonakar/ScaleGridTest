
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import models.Contact;
import models.MailingCache;
import models.Preferences;
import models.User;
import play.jobs.Every;
import play.jobs.Job;
import play.test.Fixtures;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kaleg
 */
@Every("1min")
public class EmailCronJob extends Job{
    public void doJob() {
        // Check if the database is empty
        System.out.println("count on job start "+MailingCache.count());
        if(MailingCache.count() > 0)
            checkForPendingMails();
       
    }

    private void checkForPendingMails() {
        List<MailingCache> pendingMails = MailingCache.findAll();
        for(MailingCache pendingMail : pendingMails){
            System.out.println("pending mail schedule " + pendingMail.getScheduledTime().getTime());
            System.out.println("sys schedule " + System.currentTimeMillis());
            if(pendingMail.getScheduledTime().getTime() <= System.currentTimeMillis()){
                EmailTask emailTask = new EmailTask( pendingMail.getRecipientEmail() , pendingMail.getContactName() , pendingMail.getContactEmail());
                Thread thread = new Thread(emailTask);
                System.out.println("Thread Starting");
                thread.start();
                pendingMail.delete();
            }
        }
    }
    
}
