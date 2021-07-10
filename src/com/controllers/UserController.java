/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.models.User;
import com.views.admin.ViewRead;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author cemil
 */
public class UserController {
    User user;
    public UserController(User user) {
        this.user = user;
    }

    public Object[][] getUser(){
        return user.getDataUser();
    }
    
    public Object[] getUserById(String id){
        return user.getDataUserById(id);
    }
    
    public boolean update(String id, String old_email, String email, String nama, String nohp, String password){
        Object[][] users = user.getDataUser();
        boolean sudahada = false;
        if(!email.equals(old_email)){
            for (int i = 0;users[i][0] != null; i++) {
                if(users[i][1].equals(email)){
                    sudahada = true;
                    break;
                }
            }
        }
        if(sudahada){
            JOptionPane.showMessageDialog(null,"Email sudah terdaftar pada akun lain!");
        }else{
            user.update(id, email, nama, nohp, password);
            JOptionPane.showMessageDialog(null,"Berhasil Mengupdate Akun!");
        }
        
        return !sudahada;
    }
    
    public void tampilUser(ViewRead view){
        String[] col = {"nama","email","role","password"};
        view.setjTable1(new JTable(user.getDataUser(),col));
    }
}
