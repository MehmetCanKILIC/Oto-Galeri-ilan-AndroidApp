package com.example.mck.otogalerici.Models;

public class ResimEklePojoSinifi {
    private boolean tf;
    private String sonuc;

    public void setTf(boolean tf) {
        this.tf = tf;
    }

    public boolean isTf() {
        return tf;
    }

    public void setSonuc(String sonuc) {
        this.sonuc = sonuc;
    }

    public String getSonuc() {
        return sonuc;
    }

    @Override
    public String toString() {
        return
                "ResimEklePojoSinifi{" +
                        "tf = '" + tf + '\'' +
                        ",sonuc = '" + sonuc + '\'' +
                        "}";
    }
}
