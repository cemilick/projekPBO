/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.models.DBConectivity;
import com.models.Pekerjaan;
import com.models.PekerjaanUser;
import javax.swing.JOptionPane;

/**
 *
 * @author cemil
 */
public class PekerjaanController {
    Pekerjaan job;
    public PekerjaanController(Pekerjaan job) {
        this.job = job;
    }

    public void store(String wilayah, String pekerja, String tanggal, String waktu, String biaya){
        try{
            boolean sudahada = false;
            Object[][] jobs = job.getDataJadwal();
            for (int i = 0;jobs[i][0] != null; i++) {
                if(jobs[i][1].equals(wilayah) && jobs[i][3].equals(tanggal)){
                    sudahada = true;
                    break;
                }
            }
            if(sudahada){
                JOptionPane.showMessageDialog(null,"Wilayah dan Tanggal sudah ada di Database!");
            } else {
                job.store(wilayah, pekerja, tanggal, waktu, biaya);
                JOptionPane.showMessageDialog(null,"Berhasil Menambahkan Data!");
            }
        }catch(Exception err){
            System.out.println(err);
        }
    }
    
    public Object[][] getJadwal(){
        return job.getDataJadwal();
    }
    
    public Object[][] getJadwalForUser(){
        return job.getDataJadwalForUser();
    }
    
    public Object[] getJadwalById(String id){
        return job.getDataJadwalById(id);
    }
    
    public boolean update(String id, String wilayah, String pekerja, String tanggal, String waktu, String biaya, String status){
        try{
            PekerjaanUser jobusers = new PekerjaanUser(new DBConectivity());
            if(jobusers.hitung(id) > Integer.parseInt(pekerja)){
                JOptionPane.showMessageDialog(null,"Jumlah Pekerja Kurang dari yang sudah Ada!");
            } else {
                boolean sudahada = false;
                Object[][] pekerjaan = job.getDataJadwal();
                for (int i = 0; pekerjaan[i][0] != null; i++) {
                    if(pekerjaan[i][1].toString().equals(wilayah) && pekerjaan[i][6].toString().equals("Belum Selesai") && pekerjaan[i][3].toString().equals(tanggal) && !pekerjaan[i][0].toString().equals(id)){
                        sudahada = true;
                        break;
                    }
                }
                if(sudahada){
                    JOptionPane.showMessageDialog(null,"Wilayah dan Tanggal sudah ada di Database!"); 
                } else {
                    job.update(id, wilayah, pekerja, tanggal, waktu, biaya, status);
                    JOptionPane.showMessageDialog(null,"Berhasil Mengupdate Data!");
                    return true;
                }
            };
        }catch(Exception err){
            System.out.println(err);
        }
        return false;
    }
    
    public void delete(String id){
        job.delete(id);
        JOptionPane.showMessageDialog(null,"Berhasil Menghapus Data!");
    }
    
}
