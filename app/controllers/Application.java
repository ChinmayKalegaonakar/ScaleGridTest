package controllers;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import play.*;
import play.mvc.*;

import java.util.*;
import java.util.logging.Level;

import models.*;
import play.data.validation.Required;


public class Application extends Controller {

    public static void index() {
        String name = Security.connected();
        List<Contact> contacts = Contact.find("user.email" , name).fetch();
        Preferences perfs = Preferences.find("user.email", name).first();
        render(contacts , name ,perfs);
    }
    
    public static void editPreferences(
      String startTime,
      int emailAlertOffset
    ){
        
        System.out.println(startTime);
        
       
       DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
       Date d = null;
        try {
            d = dateFormat.parse(startTime);
            Calendar calender = Calendar.getInstance();
            calender.setTime(d);
            int hours = calender.get(Calendar.HOUR_OF_DAY);
            int minutes = calender.get(Calendar.MINUTE);
            Preferences userPerf = Preferences.find("user.email", Security.connected()).first();
            userPerf.setStartTime(Integer.parseInt(hours+""+minutes));
            userPerf.setEmailAlertOffset(2);
            userPerf.save();
            
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        index();
    }
    
    public static void newContact(
        @Required(message="Name Cannot be blank") String contactName,
        @Required(message="Email Cannot be blank")String contactEmail,
        @Required(message="Birthday Cannot be blank")String contactBirthday,
        @Required(message="Timezone Cannot be blank")String contactTimezone
    ){
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date contactBirthdayDate = null;
        try {
            contactBirthdayDate = format.parse(contactBirthday);
        } catch (ParseException e) {

        }
        
        if(!validation.hasErrors()) {
            String userName = Security.connected();
            User user = User.find("email", userName).first();
            Contact newContact = new Contact( contactName , contactEmail , contactTimezone , contactBirthdayDate , user);
            flash.success("Contact Added!");
                
            newContact.save();
            
        }
        
        index();
    }

}