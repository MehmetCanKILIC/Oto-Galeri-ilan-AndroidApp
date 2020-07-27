package com.example.mck.otogalerici.Models;

public class DogrulamaPojoSinifi {
    private String result;
    private boolean tf;
    private String kadi;
    private String id;

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

    public void setKadi(String kadi) {
        this.kadi = kadi;
    }

    public String getKadi() {
        return kadi;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "DogrulamaPojoSinifi{" +
                        "result = '" + result + '\'' +
                        ",tf = '" + tf + '\'' +
                        ",kadi = '" + kadi + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}
