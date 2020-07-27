package com.example.mck.otogalerici.Models;

public class SliderPojoSinifi{
	private String resim;

	public void setResim(String resim){
		this.resim = resim;
	}

	public String getResim(){
		return resim;
	}

	@Override
 	public String toString(){
		return 
			"SliderPojoSinifi{" + 
			"resim = '" + resim + '\'' + 
			"}";
		}
}
