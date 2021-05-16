/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

/**
 *
 * @author kaleg
 */
@Entity 
public class Contact extends Model {
    public String name;
    public String email;
    public String timezone;
    public Date birthday;
    
    @ManyToOne
    public User user;

    public Contact(String name, String email, String timezone, Date birthday, User user) {
        this.name = name;
        this.email = email;
        this.timezone = timezone;
        this.birthday = birthday;
        this.user = user;
    }
   public Contact save(Contact newContact){
       return this.save(newContact);
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
   
   

    @Override
    public String toString() {
        return "Contact{" + "name=" + name + ", email=" + email + ", timezone=" + timezone + ", birthday=" + birthday + ", user=" + user + '}';
    }
   
   
}
