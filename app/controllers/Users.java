/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import org.hibernate.annotations.Check;
import play.mvc.With;

/**
 *
 * @author kaleg
 */
@With(Secure.class)
public class Users extends CRUD {
    
}
