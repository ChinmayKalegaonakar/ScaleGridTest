/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import play.db.jpa.Model;

/**
 *
 * @author kaleg
 */
@Entity
public class User extends Model {
    @Id
    private int id;
    private String name;
    private String email;
    private String password;
    private Date birthday;

    public User(String name, String email, String password, Date birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }
    
        
    
}
