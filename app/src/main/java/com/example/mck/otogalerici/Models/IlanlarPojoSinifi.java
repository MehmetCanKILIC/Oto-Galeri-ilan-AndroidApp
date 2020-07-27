package com.example.mck.otogalerici.Models;

public class IlanlarPojoSinifi {
    private Object result;
    private String resim;
    private boolean tf;
    private String il;
    private String aciklama;
    private String ilce;
    private String uyeid;
    private String ilanid;
    private String fiyat;
    private String mahalle;
    private String baslik;

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getResim() {
        return resim;
    }

    public void setTf(boolean tf) {
        this.tf = tf;
    }

    public boolean isTf() {
        return tf;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getIl() {
        return il;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public String getIlce() {
        return ilce;
    }

    public void setUyeid(String uyeid) {
        this.uyeid = uyeid;
    }

    public String getUyeid() {
        return uyeid;
    }

    public void setIlanid(String ilanid) {
        this.ilanid = ilanid;
    }

    public String getIlanid() {
        return ilanid;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setMahalle(String mahalle) {
        this.mahalle = mahalle;
    }

    public String getMahalle() {
        return mahalle;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getBaslik() {
        return baslik;
    }

    @Override
    public String toString() {
        return
                "Response{" +
                        "result = '" + result + '\'' +
                        ",resim = '" + resim + '\'' +
                        ",tf = '" + tf + '\'' +
                        ",il = '" + il + '\'' +
                        ",aciklama = '" + aciklama + '\'' +
                        ",ilce = '" + ilce + '\'' +
                        ",uyeid = '" + uyeid + '\'' +
                        ",ilanid = '" + ilanid + '\'' +
                        ",fiyat = '" + fiyat + '\'' +
                        ",mahalle = '" + mahalle + '\'' +
                        ",baslik = '" + baslik + '\'' +
                        "}";
    }
}
