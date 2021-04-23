package com.example.nusafit.entity;

public class POJOdet_transaksi {
    public int id_det_transaksi;
    public int id_transaksi;
    public int id_produk;
    public int kuantiti;
    public int total;

    public int getId_det_transaksi() {
        return id_det_transaksi;
    }

    public void setId_det_transaksi(int id_det_transaksi) {
        this.id_det_transaksi = id_det_transaksi;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public int getKuantiti() {
        return kuantiti;
    }

    public void setKuantiti(int kuantiti) {
        this.kuantiti = kuantiti;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String email_user;
}
