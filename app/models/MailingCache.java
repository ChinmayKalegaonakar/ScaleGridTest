/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 *
 * @author kaleg
 */
@Entity
public class MailingCache extends Model {
    
    public String identifier;
    public String recipientEmail;
    public String contactName;
    public String contactEmail;
    public Date scheduledTime;

    public MailingCache(String identifier, String recipientEmail, String contactName, Date scheduledTime, String contactEmail) {
        this.identifier = identifier;
        this.recipientEmail = recipientEmail;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.scheduledTime = scheduledTime;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
    
    

    
        
    
}
