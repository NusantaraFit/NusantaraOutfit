package com.example.nusafit.entity;

import org.w3c.dom.Text;

public class POJOproduk {
     public int id_product;
     public String nama_product;
     public int harga;
     public int stok;
     public Text spesifikasi;
     public String file;

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getNama_product() {
        return nama_product;
    }

    public void setNama_product(String nama_product) {
        this.nama_product = nama_product;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public Text getSpesifikasi() {
        return spesifikasi;
    }

    public void setSpesifikasi(Text spesifikasi) {
        this.spesifikasi = spesifikasi;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
