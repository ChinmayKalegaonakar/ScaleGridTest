
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import models.Contact;
import models.MailingCache;
import models.Preferences;
import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
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
@OnApplicationStart
public class Bootstrap extends Job{
    public void doJob() {
        // Check if the database is empty
        
        if(User.count() == 0) {
            Fixtures.loadModels("initial-data.yml");
        }
        
        MailCacheLoadingService service = new MailCacheLoadingService();
        service.insertPendingMails();
    }
    
    
    
    
    
}
