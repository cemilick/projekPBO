/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.sql.ResultSet;

/**
 *
 * @author cemil
 */
public class PekerjaanUser {
    DBConectivity DB;
    public PekerjaanUser(DBConectivity DB){
        this.DB = DB;
    }
    
    public void apply(String id, String id_user){
        try{
            DB.statement = DB.koneksi.createStatement();
            String query = "INSERT INTO `jobusers` (`id_jobusers`, `id_job`, `id_user`) VALUES (NULL, '"+id+"', '"+id_user+"')";
            DB.statement.execute(query);
            
            DB.statement.close();
        } catch(Exception err){
            System.out.println(err);
        }
    }
    
    public Object[][] getDataPekerjaanUser(){
        String arr[][] = new String[100][5];
        
        try{
            DB.statement = DB.koneksi.createStatement();
            ResultSet result = DB.statement.executeQuery("SELECT * FROM jobusers INNER JOIN jobs ON jobusers.id_job = jobs.id INNER JOIN users ON jobusers.id_user = users.id");
            
            int i = 0;
            while(result.next()){
                arr[i][0] = result.getString("id_jobusers");
                arr[i][1] = result.getString("nama");
                arr[i][2] = result.getString("wilayah");
                arr[i][3] = result.getString("tanggal");
                arr[i][4] = result.getString("status");
                i++;
            }
            DB.statement.close();
        } catch(Exception err){
            System.out.println(err);
        }
        
        return arr;
    }
    
    public Object[][] getDataPekerjaanUserById(String id){
        Object[][] arr = new Object[100][3];
        
        try{
            DB.statement = DB.koneksi.createStatement();
            ResultSet result = DB.statement.executeQuery("SELECT * FROM jobusers INNER JOIN jobs ON jobusers.id_job = jobs.id WHERE jobusers.id_user = '"+ id +"'");
            int i = 0;
            while(result.next()){
                arr[i][0] = result.getString("wilayah");
                arr[i][1] = result.getString("tanggal");
                arr[i][2] = result.getString("status");
                i++;
            }
            DB.statement.close();
        } catch(Exception err){
            System.out.println(err);
        }
        return arr;
    }
    
    public int hitung(String id_job){
            int i = 0;
        try{
            DB.statement = DB.koneksi.createStatement();
            ResultSet result = DB.statement.executeQuery("SELECT * FROM jobusers WHERE id_job = '"+ id_job +"'");
            
            while(result.next()){
                i++;
            }
            DB.statement.close();
        } catch(Exception err){
            System.out.println(err);
        }
        return i;
    }
    
    public void delete(String id){
        try{
            DB.statement = DB.koneksi.createStatement(); 
            String query = "DELETE FROM `jobusers` WHERE `jobusers`.`id_jobusers` = \'"+id+"\'";
            DB.statement.execute(query);
            DB.statement.close();
        }catch(Exception err){
            System.out.println(err);
        }
    }
}
