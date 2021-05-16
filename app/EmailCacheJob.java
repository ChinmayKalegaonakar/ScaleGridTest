
import models.MailingCache;
import play.jobs.Every;
import play.jobs.Job;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kaleg
 */
@Every("1h")
public class EmailCacheJob extends Job {
     public void doJob() {
        
        MailCacheLoadingService service = new MailCacheLoadingService();
        service.insertPendingMails();
       
    }
}
