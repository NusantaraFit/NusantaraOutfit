package com.example.nusafit.entity;

public class POJOkategori {
    public int id_kategor;
    public String nama_kategori;
    public int id_produk;

    public int getId_kategor() {
        return id_kategor;
    }

    public void setId_kategor(int id_kategor) {
        this.id_kategor = id_kategor;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }
}
