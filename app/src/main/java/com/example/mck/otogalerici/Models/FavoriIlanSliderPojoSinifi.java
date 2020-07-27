package com.example.mck.otogalerici.Models;

public class FavoriIlanSliderPojoSinifi{
	private String resimyolu;
	private boolean tf;
	private String ilanid;

	public void setResimyolu(String resimyolu){
		this.resimyolu = resimyolu;
	}

	public String getResimyolu(){
		return resimyolu;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setIlanid(String ilanid){
		this.ilanid = ilanid;
	}

	public String getIlanid(){
		return ilanid;
	}

	@Override
	public String toString(){
		return
				"Response2{" +
						"resimyolu = '" + resimyolu + '\'' +
						",tf = '" + tf + '\'' +
						",ilanid = '" + ilanid + '\'' +
						"}";
	}
}
