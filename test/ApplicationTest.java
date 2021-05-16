import java.util.Date;
import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;
import static org.junit.Assert.assertEquals;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }
    
    @Test
    public void cronDateShifting(){
        User user1 = new User("chinmay", "chinmay@gmail.com", "secret123", new Date()).save();
        Contact contact1 = new Contact("chinmayContact", "chinmay2@gmail.com", "IST", new Date(System.currentTimeMillis()+20000), user1).save();
        Contact contact2 = new Contact("chinmayContact2", "chinmay4@gmail.com", "PST", new Date(System.currentTimeMillis()+20000), user1).save();
        Preferences perfs = new Preferences("IST", 8 , 1, user1).save();
        MailCacheLoadingService service = new MailCacheLoadingService();
        service.insertPendingMails();
        
        assertEquals(1 , MailingCache.count());
       
    }
    
}