/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.sql.DriverManager;
import java.sql.Connection; 
import java.sql.SQLException; 
import java.sql.Statement;

/**
 *
 * @author cemil
 */
public class DBConectivity{
    String DBurl = "jdbc:mysql://localhost/perbaikan";
    String DBusername = "root";
    String DBpassword = "";
    public Connection koneksi;
    public Statement statement;
    
    public DBConectivity() throws SQLException{  
        try{  
           Class.forName("com.mysql.jdbc.Driver");
           koneksi = (Connection) DriverManager.getConnection(DBurl, DBusername, DBpassword);
           
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }
}
