package com.example.nusafit.entity;

import java.util.Date;

public class POJOkonfirmasi {
    public int id_konfirmasi;
    public int id_transaksi;
    public Date tgl_konfirmasi;
    public String metode_pembayaran;
    public String customer_bank;
    public String customer_name;
    public int customer_rek;

    public int getId_konfirmasi() {
        return id_konfirmasi;
    }

    public void setId_konfirmasi(int id_konfirmasi) {
        this.id_konfirmasi = id_konfirmasi;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public Date getTgl_konfirmasi() {
        return tgl_konfirmasi;
    }

    public void setTgl_konfirmasi(Date tgl_konfirmasi) {
        this.tgl_konfirmasi = tgl_konfirmasi;
    }

    public String getMetode_pembayaran() {
        return metode_pembayaran;
    }

    public void setMetode_pembayaran(String metode_pembayaran) {
        this.metode_pembayaran = metode_pembayaran;
    }

    public String getCustomer_bank() {
        return customer_bank;
    }

    public void setCustomer_bank(String customer_bank) {
        this.customer_bank = customer_bank;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getCustomer_rek() {
        return customer_rek;
    }

    public void setCustomer_rek(int customer_rek) {
        this.customer_rek = customer_rek;
    }

    public int getRek_konfirmasi() {
        return rek_konfirmasi;
    }

    public void setRek_konfirmasi(int rek_konfirmasi) {
        this.rek_konfirmasi = rek_konfirmasi;
    }

    public int rek_konfirmasi;

}
