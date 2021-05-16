
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import models.Contact;
import models.MailingCache;
import models.Preferences;
import models.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kaleg
 */
public class MailCacheLoadingService {
    
    public static boolean isIn24HourPeriod(Calendar cal1 , Calendar cal2){
        return Math.abs(cal2.getTimeInMillis() - cal1.getTimeInMillis()) < 86400000 ? true: false; 
    }
    
    public static Calendar convertedDate(Contact contact , Calendar birthday , Calendar myTimezone){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss XXX");
        String timeOffset = "";
        int minutesOffset = 0;
        int hoursOffset = 0;
        int alertOffset = 0;
        char sign = '+';
        
        try{
            sdf.setTimeZone(TimeZone.getTimeZone(contact.getTimezone()));
            timeOffset = sdf.format(contact.getBirthday()).split(" ")[2];
            sign = timeOffset.charAt(0);
            minutesOffset = Integer.parseInt(timeOffset.substring(1, timeOffset.length()).split(":")[1]);
            hoursOffset = Integer.parseInt(timeOffset.substring(1, timeOffset.length()).split(":")[0]);
            birthday.setTime(contact.getBirthday());
            birthday.set(Calendar.YEAR , myTimezone.get(Calendar.YEAR));
            if(sign == '+'){
                birthday.add(Calendar.HOUR , -hoursOffset-alertOffset);
                birthday.add(Calendar.MINUTE , -minutesOffset);
            }else{
                birthday.add(Calendar.HOUR , +hoursOffset-alertOffset);
                birthday.add(Calendar.MINUTE , +minutesOffset);
            }
        }catch(Exception e){
            System.out.println( "error in "+ e.getMessage());
        }
        return birthday;
    }
    
    public void insertPendingMails() {
        Preferences perfs = null;
        List<Contact> contacts = null;
        Calendar myTimezone = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss XXX");
        
        String timeOffset = "";
        int minutesOffset = 0;
        int hoursOffset = 0;
        int alertOffset = 0;
        char sign = '+';
        if(User.count() > 0){
            List<User> users = User.all().fetch();
            for( User user : users){
                perfs = Preferences.find("user.email", user.getEmail()).first();
                alertOffset = perfs.getEmailAlertOffset();
                contacts = Contact.find("user.email" , user.getEmail()).fetch();
                
                for( Contact contact : contacts ){
                    // already added this object in cache
                    if(MailingCache.count() != 0 && MailingCache.find("identifier",user.getName()+"-"+contact.getName() ).fetch().size() == 0){
                        return;
                    }
                    try{
                        birthday = convertedDate(contact , birthday , myTimezone);
                    }catch(Exception e){
                        System.out.println( "error in "+ e.getMessage());
                    }
                    
                    
                        if(isIn24HourPeriod(myTimezone , birthday )){
                            if((birthday.getTimeInMillis() - myTimezone.getTimeInMillis()) > 0 ){ // dont insert earlier date
                               MailingCache mailingCacheObject = new MailingCache(user.getName()+"-"+contact.getName() ,user.getEmail(), contact.getName() , birthday.getTime(), contact.getEmail());
                               mailingCacheObject.save();
                               System.out.println("Inserted into mailing cache " + birthday.getTime());
                            }

                        }
                    
                }
            }
            
        }
    }
    
}
