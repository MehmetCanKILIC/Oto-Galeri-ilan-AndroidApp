package com.example.mck.otogalerici.RestApi;


import com.example.mck.otogalerici.IlanResimler;
import com.example.mck.otogalerici.Models.DogrulamaPojoSinifi;
import com.example.mck.otogalerici.Models.FavoriIlanSliderPojoSinifi;
import com.example.mck.otogalerici.Models.FavoriIslemPojoSinifi;
import com.example.mck.otogalerici.Models.FavoriKontrolPojoSinifi;
import com.example.mck.otogalerici.Models.GuncellePojoSinifi;
import com.example.mck.otogalerici.Models.IlanDetayPojoSinifi;
import com.example.mck.otogalerici.Models.IlanSilmePojoSinifi;
import com.example.mck.otogalerici.Models.IlanSonucPojoSinifi;
import com.example.mck.otogalerici.Models.IlanlarPojoSinifi;
import com.example.mck.otogalerici.Models.IlanlarimPojoSinifi;
import com.example.mck.otogalerici.Models.LoginPojoSinifi;
import com.example.mck.otogalerici.Models.RegisterPojoSinifi;
import com.example.mck.otogalerici.Models.ResimEklePojoSinifi;
import com.example.mck.otogalerici.Models.SliderPojoSinifi;
import com.example.mck.otogalerici.Models.UserPojoSinifi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface RestApi {
    // login Web-servisi post etme işlemi
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginPojoSinifi> control(@Field("kadi") String ad, @Field("sifre") String sifre);

    // register Web-servisi post etme işlemi
    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterPojoSinifi> kayitol(@Field("kadi") String ad, @Field("sifre") String sifre);

    //Dogrulama web-servisi post etme işlemi
    @FormUrlEncoded
    @POST("dogrulama.php")
    Call<DogrulamaPojoSinifi> dogrulama(@Field("kadi") String ad, @Field("kod") String kod);

    //İlan verme web servisi post etme işlemi
    @FormUrlEncoded
    @POST("ilanver.php")
    Call<IlanSonucPojoSinifi> ilanVer(@Field("uye_id") String uye_id, @Field("sehir") String sehir, @Field("ilce") String ilce, @Field("mahalle") String mahalle, @Field("marka") String marka, @Field("seri") String seri, @Field("model") String model, @Field("yil") String yil, @Field("ilantipi") String ilantipi, @Field("kimden") String kimden, @Field("baslik") String baslik, @Field("aciklama") String aciklama, @Field("motortipi") String motortipi, @Field("motorhacmi") String motorhacmi, @Field("surat") String surat, @Field("yakittipi") String yakittipi, @Field("ortalamayakit") String ortalamayakit, @Field("depohacmi") String depohacmi, @Field("km") String km, @Field("ucret") String ucret);

    //ilanaresimekle web-servisi post etme işlemi
    @FormUrlEncoded
    @POST("ilanresmiekle.php")
    Call<ResimEklePojoSinifi> resimYukle(@Field("uye_id") String uye_id, @Field("ilan_id") String ilan_id, @Field("resim") String base64StringResim);

    //İlanlarim web-servisi GET etme işlemi
    @GET("ilanlarim.php")
    Call<List<IlanlarimPojoSinifi>> ilanlarim(@Query("uyeid") String uyeid);

    //Kişisel İlanlarim silme web-servisi GET etme işlemi
    @GET("ilanlarimdansil.php")
    Call<IlanSilmePojoSinifi> ilanlarimSil(@Query("ilan_id") String ilanid);

    //İlanlar web-servisi GET etme işlemi
    @GET("ilanlar.php")
    Call<List<IlanlarPojoSinifi>> ilanlar();

    //ilan detay web-servisi GET etme işlemi
    @GET("ilandetay.php")
    Call<IlanDetayPojoSinifi> ilanDetay(@Query("ilanid") String ilanid);

    //ilan detay web-servisi GET etme işlemi
    @GET("ilanresimleri.php")
    Call<List<SliderPojoSinifi>> ilanResimleri(@Query("ilanid") String ilanid);

    //favori web-servisi GET etme işlemi
    @GET("favori.php")
    Call<FavoriKontrolPojoSinifi> getButonText(@Query("uye_id") String uyeid, @Query("ilan_id") String ilanid);

    //favoriislem web-servisi GET etme işlemi
    @GET("favoriislem.php")
    Call<FavoriIslemPojoSinifi> favoriIslem(@Query("uye_id") String uyeid, @Query("ilan_id") String ilanid);

    //favoriilanslider web-servisi GET etme işlemi
    @GET("favoriilanslider.php")
    Call<List<FavoriIlanSliderPojoSinifi>> setSlider(@Query("uyeid") String uyeid);

    //kişisel bilgiler web-servisi GET etme işlemi
    @GET("bilgiler.php")
    Call<UserPojoSinifi> getInformation(@Query("uyeid") String uyeid);

    //kişisel bilgileri güncelleme web-servisi GET etme işlemi
    @GET("guncelle.php")
    Call<GuncellePojoSinifi> chandeInformation(@Query("uyeid") String uyeid, @Query("user") String user, @Query("pass") String pass);
}
