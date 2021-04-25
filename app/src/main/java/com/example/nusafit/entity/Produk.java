package com.example.nusafit.entity;

public class Produk {
    private String Nama_produk;
    private String Deskripsi;
    private String Harga;
    private String Warna;
    private String Ukuran;
    private String Key;

    public Produk(String namaProduk, String deskProduk, String hargaProduk, String warnaProduk, String ukuranProduk) {
        this.Nama_produk = namaProduk;
        this.Deskripsi = deskProduk;
        this.Harga = hargaProduk;
        this.Warna = warnaProduk;
        this.Ukuran = ukuranProduk;
    }

    public String getNama_produk() {
        return Nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        Nama_produk = nama_produk;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getWarna() {
        return Warna;
    }

    public void setWarna(String warna) {
        Warna = warna;
    }

    public String getUkuran() {
        return Ukuran;
    }

    public void setUkuran(String ukuran) {
        Ukuran = ukuran;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
