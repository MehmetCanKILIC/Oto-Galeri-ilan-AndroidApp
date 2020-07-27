package com.example.mck.otogalerici.Models;

public class LoginPojoSinifi {
    private String kadi;
    private String id;

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
                "LoginPojoSinifi{" +
                        "kadi = '" + kadi + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}
