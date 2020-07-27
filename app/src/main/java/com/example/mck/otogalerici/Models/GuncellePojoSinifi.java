package com.example.mck.otogalerici.Models;

public class GuncellePojoSinifi{
	private boolean tf;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	@Override
 	public String toString(){
		return 
			"GuncellePojoSinifi{" + 
			"tf = '" + tf + '\'' + 
			"}";
		}
}
