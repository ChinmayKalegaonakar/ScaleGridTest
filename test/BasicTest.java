import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {
    
    @Before
    public void setup() {
        Fixtures.deleteAll();
    }

    
    
    
    @Test
    public void usersCheck(){
        User user1 = new User("chinmay", "chinmay@gmail.com", "secret123", new Date()).save();
        User retrived = User.find("email","chinmay@gmail.com").first();
        
        assertEquals("chinmay", retrived.getName());
        
    }
    
    
    @Test
    public void contactsCheck(){
        User user1 = new User("chinmay", "chinmay@gmail.com", "secret123", new Date()).save();
        Contact contact1 = new Contact("chinmayContact", "chinmay2@gmail.com", "IST", new Date(System.currentTimeMillis()+20000), user1).save();
        
        Contact retrived = Contact.find("name", contact1.getName()).first();
        
        assertEquals("chinmay2@gmail.com" , retrived.getEmail());
        
    }
    @Test
    public void cronjobcheck() {
        User user1 = new User("chinmay", "chinmay@gmail.com", "secret123", new Date()).save();
        Contact contact1 = new Contact("chinmayContact", "chinmay2@gmail.com", "IST", new Date(System.currentTimeMillis()+20000), user1).save();
        Contact contact2 = new Contact("chinmayContact2", "chinmay4@gmail.com", "PST", new Date(System.currentTimeMillis()+20000), user1).save(); // wont be saved into mailing
        Preferences perfs = new Preferences("IST", 8 , 1, user1).save();
        MailCacheLoadingService service = new MailCacheLoadingService();
        service.insertPendingMails();
        
        assertEquals(1 , MailingCache.count());
       
    }
    
    @Test
    public void checkDateNotEqual() {
        User user1 = new User("chinmay", "chinmay@gmail.com", "secret123", new Date()).save();
        
        Date fixedDate = new Date(System.currentTimeMillis()-TimeZone.getTimeZone("PST").getRawOffset());
        Contact contact2 = new Contact("chinmayContact2", "chinmay4@gmail.com", "PST", fixedDate, user1).save(); // converting from PST to IST
        Preferences perfs = new Preferences("IST", 8 , 10, user1).save();
        MailCacheLoadingService service = new MailCacheLoadingService();
        
        Calendar myTimezone = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();
        
        Calendar result = MailCacheLoadingService.convertedDate(contact2, birthday, myTimezone);
        
        assertEquals("Asia/Calcutta" , result.getTimeZone().getID() ); 
       
    }

}
