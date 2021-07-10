/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models; 

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cemil
 */
public class User {
    static DBConectivity DB;
    public String id;
    public String email;
    public String role;
    
    public User(DBConectivity DB){
        this.DB = DB;
    }
    
    public User(DBConectivity DB, String email, String role) throws SQLException {
        this.DB = DB;
        this.id = User.getUserId(email);
        this.email = email;
        this.role = role;
    }
    
    public static String getUserId(String email){
        String id = null;
        String query = "";
        try{
            DB.statement = DB.koneksi.createStatement();
            query = "SELECT * FROM users WHERE email = '"+email+"'";
            ResultSet result = DB.statement.executeQuery(query);
            
            while(result.next()){
                id = result.getString("id");
            }
            DB.statement.close();
        } catch(Exception err){
            System.out.println(query+" mampus : " + err);
        }
        return id;
    }
    
    public Object[] getDataUserById(String id){
        Object arr[] = new Object[4];
        
        try{
            DB.statement = DB.koneksi.createStatement();
            ResultSet result = DB.statement.executeQuery("SELECT * FROM `users` WHERE id = '"+id+"'");
            
            while(result.next()){
                arr[0] = result.getString("nama");
                arr[1] = result.getString("email");
                arr[2] = result.getString("nohp");
                arr[3] = result.getString("password");
            }
            DB.statement.close();
        } catch(Exception err){
            System.out.println(err);
        }
        
        return arr;
    }
    
    public void update(String id, String email, String nama, String nohp, String password){
        try{
            DB.statement = DB.koneksi.createStatement();
            String query = "UPDATE `users` SET `email` = '"+email+"', `nama` = '"+nama+"', `nohp` = '"+nohp+"', `password` = '"+password+"' WHERE `users`.`id` = "+id;
            DB.statement.execute(query);
            
            DB.statement.close();
        } catch(Exception err){
            System.out.println(err);
        }
    }
    
    public Object[][] getDataUser() {
        String arr[][] = new String[100][4];
        
        try{
            DB.statement = DB.koneksi.createStatement();
            ResultSet result = DB.statement.executeQuery("SELECT * FROM `users`");
            
            int i = 0;
            while(result.next()){
                arr[i][0] = result.getString("nama");
                arr[i][1] = result.getString("email");
                arr[i][2] = result.getString("role");
                arr[i][3] = result.getString("password");
                i++;
            }
            DB.statement.close();
        } catch(Exception err){
            System.out.println(err);
        }
        
        return arr;
    }
    
    public boolean store(String nama,String email, String password, String nohp, String role){
        try{
            DB.statement = DB.koneksi.createStatement();
            String query = "INSERT INTO `users` (`id`, `nama`, `role`, `email`, `password`, `nohp`) VALUES (NULL, '" + nama + "','" + role + "','" + email + "','" + password + "', '"+nohp+"')";
            DB.statement.execute(query);
            return true;
        } catch(Exception err){
            System.out.println(err);
        }
        return false;
    }
    
    public boolean delete(String id){
        try{
            DB.statement = DB.koneksi.createStatement(); 
            String query = "DELETE FROM `users` WHERE `users`.`id` = \'"+id+"\'";
            DB.statement.execute(query);
            DB.statement.close();
            return true;
        }catch(Exception err){
            System.out.println(err);
        }
        return false;
    }
}
