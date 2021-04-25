package com.example.nusafit.entity;

public class Produk {
    private String Nama_produk;
    private String Deskripsi;
    private Integer Harga;
    private String Warna;
    private Integer Ukuran;

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

    public Integer getHarga() {
        return Harga;
    }

    public void setHarga(Integer harga) {
        Harga = harga;
    }

    public String getWarna() {
        return Warna;
    }

    public void setWarna(String warna) {
        Warna = warna;
    }

    public Integer getUkuran() {
        return Ukuran;
    }

    public void setUkuran(Integer ukuran) {
        Ukuran = ukuran;
    }
}
