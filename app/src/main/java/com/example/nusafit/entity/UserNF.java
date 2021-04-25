package com.example.nusafit.entity;

public class UserNF {
    private String namalengkap, jenkel, ttl, alamat, agama, email;
    private String key;

    public UserNF(String namalengkap, String jenkel, String ttl, String alamat, String agama, String email) {
        this.namalengkap = namalengkap;
        this.jenkel = jenkel;
        this.ttl = ttl;
        this.alamat = alamat;
        this.agama = agama;
        this.email = email;
    }

    public UserNF(String namalengkap, String email) {
        this.namalengkap = namalengkap;
        this.email = email;
    }

    public UserNF() {
    }

    public String getNamalengkap() {
        return namalengkap;
    }

    public void setNamalengkap(String namalengkap) {
        this.namalengkap = namalengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getJenkel() {
        return jenkel;
    }

    public void setJenkel(String jenkel) {
        this.jenkel = jenkel;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
