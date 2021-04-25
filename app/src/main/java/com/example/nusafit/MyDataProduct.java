package com.example.nusafit;

public class MyDataProduct {
    private Integer src;
    private String namaProduk;
    private String hargaProduk;

    public MyDataProduct(Integer src, String namaProduk, String hargaProduk) {
        this.src = src;
        this.namaProduk = namaProduk;
        this.hargaProduk = hargaProduk;
    }

    public Integer getSrc() {
        return src;
    }

    public void setSrc(Integer src) {
        this.src = src;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getHargaProduk() {
        return hargaProduk;
    }

    public void setHargaProduk(String hargaProduk) {
        this.hargaProduk = hargaProduk;
    }


}
