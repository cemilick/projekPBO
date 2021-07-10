/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.models.PekerjaanUser;
import javax.swing.JOptionPane;

/**
 *
 * @author cemil
 */
public class PekerjaanUserController {
    PekerjaanUser job;
    public PekerjaanUserController(PekerjaanUser job){
        this.job = job;
    }
    
    public Object[][] getPekerjaanUser(){
        return job.getDataPekerjaanUser();
    }
    
    public Object[][] getPekerjaanUserById(String id){
        return job.getDataPekerjaanUserById(id);
    }
    
    public boolean apply(String id, String id_user, String pekerja){
        Object[][] jobUser = job.getDataPekerjaanUserById(id_user);
        
        boolean gagal = false;
        
        for (int i = 0;jobUser[i][0] != null; i++) {
            if(jobUser[i][2].toString().equals("Belum Selesai")){
               gagal = true;
                break;
            }
        }
             
        if(gagal){
            JOptionPane.showMessageDialog(null,"Masih terdapat Pekerjaan Anda yang Belum Selesai!");
            return false;
        }else{
            System.out.println(pekerja);
            if(job.hitung(id) < Integer.parseInt(pekerja)){
                job.apply(id, id_user);
                JOptionPane.showMessageDialog(null,"Berhasil Mengambil Pekerjaan!");
                return true;    
            } else {
                System.out.println("hai david dimana kamu????");
                JOptionPane.showMessageDialog(null,"Jumlah pekerja sudah penuh!");
                return false;
            }
        }
    }
    
    public void delete(String id){
        job.delete(id);
        
        JOptionPane.showMessageDialog(null,"Berhasil Menghapus Data!");
    }
}
