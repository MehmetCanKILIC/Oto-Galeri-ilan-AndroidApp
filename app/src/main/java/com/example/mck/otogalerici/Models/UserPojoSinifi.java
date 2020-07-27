package com.example.mck.otogalerici.Models;

public class UserPojoSinifi{
	private String sifre;
	private String kadi;

	public void setSifre(String sifre){
		this.sifre = sifre;
	}

	public String getSifre(){
		return sifre;
	}

	public void setKadi(String kadi){
		this.kadi = kadi;
	}

	public String getKadi(){
		return kadi;
	}

	@Override
 	public String toString(){
		return 
			"UserPojoSinifi{" + 
			"sifre = '" + sifre + '\'' + 
			",kadi = '" + kadi + '\'' + 
			"}";
		}
}
