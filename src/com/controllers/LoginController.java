/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.models.DBConectivity;
import com.models.User;
import javax.swing.JOptionPane;

/**
 *
 * @author cemil
 */
public class LoginController {
    Object[][] user;
    User modeluser;
    String email;
    String password;
    String nohp;
    String role = "user";
    String nama;
    
    public LoginController(User user, String email, String password){
        this.user = user.getDataUser();
        this.email = email;
        this.password = password;
    }
    
    public LoginController(User user, String nama, String email, String password, String nohp){
        this.user = user.getDataUser();
        this.email = email;
        this.password = password;
        this.nama = nama;
        this.nohp = nohp; 
    }
    
    public Object[] login(){
        for (int i = 0; user[i][0] != null; i++) {
            if(email.equals(user[i][1]) && password.equals(user[i][3])){
                return user[i];
            }
        }
        return null;
    }
    
    public boolean register(){
        for (int i = 0; user[i][0] != null; i++) {
            if(email.equals(user[i][1])){
                return false;
            }
        }
        try{
             if(new User(new DBConectivity(),nama,email).store(nama, email, password, nohp, role)){
                 return true;
             }
        } catch(Exception err){
            System.out.println(err);
        }
        return false;
    }
}
