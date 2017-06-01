package com.example.risca.androidmentoringtraining;

/**
 * Created by RISCA on 6/1/2017.
 */

public class Mahasiswa {
    private String nama, nim, alamat, jenis_kelamin, no_telp;

    public Mahasiswa(String nama, String nim, String alamat, String jenis_kelamin, String no_telp) {
        this.nama = nama;
        this.nim = nim;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
        this.no_telp = no_telp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }
}
