package com.example.mck.otogalerici.Models;

public class RegisterPojoSinifi {
    private String result;
    private boolean tf;
    private int dogrulamaKodu;

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setTf(boolean tf) {
        this.tf = tf;
    }

    public boolean isTf() {
        return tf;
    }

    public void setDogrulamaKodu(int dogrulamaKodu) {
        this.dogrulamaKodu = dogrulamaKodu;
    }

    public int getDogrulamaKodu() {
        return dogrulamaKodu;
    }

    @Override
    public String toString() {
        return
                "RegisterPojoSinifi{" +
                        "result = '" + result + '\'' +
                        ",tf = '" + tf + '\'' +
                        ",dogrulamaKodu = '" + dogrulamaKodu + '\'' +
                        "}";
    }
}
