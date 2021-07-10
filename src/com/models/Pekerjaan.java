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
public class Pekerjaan {
    DBConectivity DB;
    public Pekerjaan(DBConectivity DB){
        this.DB = DB;
    }
    
    public Object[][] getDataJadwal(){
        Object[][] jobs = new Object[1000][7];
        try{
            DB.statement = DB.koneksi.createStatement();
            ResultSet result = DB.statement.executeQuery("SELECT * FROM `jobs`");
            
            int i = 0;
            while(result.next()){
                jobs[i][0] = result.getString("id");
                jobs[i][1] = result.getString("wilayah");
                jobs[i][2] = result.getString("pekerja");
                jobs[i][3] = result.getString("tanggal");
                jobs[i][4] = result.getString("waktu");
                jobs[i][5] = result.getString("biaya");
                jobs[i][6] = result.getString("status");
                i++;
            }
            DB.statement.close();
        } catch(Exception err){
            System.out.println(err);
        }
        return jobs;
    }
    
    public Object[][] getDataJadwalForUser(){
        Object[][] jobs = new Object[1000][6];
        try{
            DB.statement = DB.koneksi.createStatement();
            ResultSet result = DB.statement.executeQuery("SELECT * FROM `jobs` WHERE jobs.status = 'Belum Selesai'");
            
            int i = 0;
            while(result.next()){
                jobs[i][0] = result.getString("id");
                jobs[i][1] = result.getString("wilayah");
                jobs[i][2] = result.getString("pekerja");
                jobs[i][3] = result.getString("tanggal");
                jobs[i][4] = result.getString("waktu");
                jobs[i][5] = result.getString("biaya");
                i++;
            }
            DB.statement.close();
        } catch(Exception err){
            System.out.println(err);
        }
        return jobs;
    }
    
    public void store(String wilayah, String pekerja, String tanggal, String waktu, String biaya){
        try{
            DB.statement = DB.koneksi.createStatement();
            String query = "INSERT INTO `jobs` (`id`, `wilayah`, `pekerja`, `tanggal`, `waktu`, `biaya`,`status`) VALUES (NULL, '"+wilayah+"', '"+pekerja+"', '"+tanggal+"', '"+waktu+"', '"+biaya+"','Belum Selesai')";
            DB.statement.execute(query);
            
            DB.statement.close();
        } catch(Exception err){
            System.out.println(err);
        }
    }
    
    public void update(String id, String wilayah, String pekerja, String tanggal, String waktu, String biaya, String status){
        try{
            DB.statement = DB.koneksi.createStatement();
            String query = "UPDATE `jobs` SET `wilayah` = '"+wilayah+"', `pekerja` = '"+pekerja+"', `tanggal` = '"+tanggal+"', `waktu` = '"+waktu+"', `biaya` = '"+biaya+"',`status`='"+status+"'  WHERE `jobs`.`id` = "+id;
            DB.statement.execute(query);
            
            DB.statement.close();
        } catch(Exception err){
            System.out.println(err);
        }
    }
    
    public Object[] getDataJadwalById(String id){
        Object[] jobs = new Object[6];
        try{
            DB.statement = DB.koneksi.createStatement();
            ResultSet result = DB.statement.executeQuery("SELECT * FROM `jobs` WHERE jobs.id = '"+id+"'");
            
            int i = 0;
            while(result.next()){
                jobs[0] = result.getString("id");
                jobs[1] = result.getString("wilayah");
                jobs[2] = result.getString("pekerja");
                jobs[3] = result.getString("tanggal");
                jobs[4] = result.getString("waktu");
                jobs[5] = result.getString("biaya");
            }
            DB.statement.close();
        } catch(Exception err){
            System.out.println(err);
        }
        return jobs;
    }
    
    public void delete(String id){
        try{
            DB.statement = DB.koneksi.createStatement(); 
            String query = "DELETE FROM `jobs` WHERE `jobs`.`id` = \'"+id+"\'";
            DB.statement.execute(query);
            DB.statement.close();
        }catch(Exception err){
            System.out.println(err);
        }
    }
}
