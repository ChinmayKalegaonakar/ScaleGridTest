/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import play.db.jpa.Model;

/**
 * Simple Class to hold user preference , needs refinement to use key value pairs
 * @TODO : refactor to use KV pairs
 * @author kaleg
 */
@Entity 
public class Preferences extends Model {
    
    public String timezone;
    public int startTime;
    public int emailAlertOffset;
    
    @OneToOne
    public User user;

    public Preferences(String timezone, int startTime, int emailAlertOffset, User user) {
        this.timezone = timezone;
        this.startTime = startTime;
        this.emailAlertOffset = emailAlertOffset;
        this.user = user;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEmailAlertOffset() {
        return emailAlertOffset;
    }

    public void setEmailAlertOffset(int emailAlertOffset) {
        this.emailAlertOffset = emailAlertOffset;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
