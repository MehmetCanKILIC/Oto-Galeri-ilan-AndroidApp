package com.example.mck.otogalerici.Models;

public class IlanlarimPojoSinifi {
    private String result;
    private Object resim;
    private boolean tf;
    private Object aciklama;
    private Object uyeid;
    private Object ilanid;
    private Object fiyat;
    private Object baslik;

    public void setResult(String result){
        this.result = result;
    }

    public String getResult(){
        return result;
    }

    public void setResim(Object resim){
        this.resim = resim;
    }

    public Object getResim(){
        return resim;
    }

    public void setTf(boolean tf){
        this.tf = tf;
    }

    public boolean isTf(){
        return tf;
    }

    public void setAciklama(Object aciklama){
        this.aciklama = aciklama;
    }

    public Object getAciklama(){
        return aciklama;
    }

    public void setUyeid(Object uyeid){
        this.uyeid = uyeid;
    }

    public Object getUyeid(){
        return uyeid;
    }

    public void setIlanid(Object ilanid){
        this.ilanid = ilanid;
    }

    public Object getIlanid(){
        return ilanid;
    }

    public void setFiyat(Object fiyat){
        this.fiyat = fiyat;
    }

    public Object getFiyat(){
        return fiyat;
    }

    public void setBaslik(Object baslik){
        this.baslik = baslik;
    }

    public Object getBaslik(){
        return baslik;
    }

    @Override
    public String toString(){
        return
                "Response{" +
                        "result = '" + result + '\'' +
                        ",resim = '" + resim + '\'' +
                        ",tf = '" + tf + '\'' +
                        ",aciklama = '" + aciklama + '\'' +
                        ",uyeid = '" + uyeid + '\'' +
                        ",ilanid = '" + ilanid + '\'' +
                        ",fiyat = '" + fiyat + '\'' +
                        ",baslik = '" + baslik + '\'' +
                        "}";
    }
}
