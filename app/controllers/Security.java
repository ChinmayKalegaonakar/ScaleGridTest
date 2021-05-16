/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.User;

/**
 *
 * @author kaleg
 */
public class Security extends Secure.Security { 

    static boolean authenticate(String username, String password) { 
        return User.connect(username, password) != null;
    }

}
