package com.example.mck.otogalerici.RestApi;


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


public class ManagerAll extends BaseManager {

    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {
        return ourInstance;
    }

    public Call<LoginPojoSinifi> login(String ad, String sifre) {
        Call<LoginPojoSinifi> x = getRestApi().control(ad, sifre);
        return x;
    }

    public Call<RegisterPojoSinifi> register(String ad, String sifre) {
        Call<RegisterPojoSinifi> x = getRestApi().kayitol(ad, sifre);
        return x;
    }

    public Call<DogrulamaPojoSinifi> dogrula(String ad, String kod) {
        Call<DogrulamaPojoSinifi> x = getRestApi().dogrulama(ad, kod);
        return x;
    }

    public Call<IlanSonucPojoSinifi> ilanVer(String uye_id, String sehir, String ilce, String mahalle, String marka, String seri, String model, String yil, String ilantipi, String kimden, String baslik, String aciklama, String motortipi, String motorhacmi, String surat, String yakittipi, String ortalamayakit, String depohacmi, String km, String ucret) {
        Call<IlanSonucPojoSinifi> x = getRestApi().ilanVer(uye_id, sehir, ilce, mahalle, marka, seri, model, yil, ilantipi, kimden, baslik, aciklama, motortipi, motorhacmi, surat, yakittipi, ortalamayakit, depohacmi, km, ucret);
        return x;
    }

    public Call<ResimEklePojoSinifi> resimEkle(String uye_id, String ilan_id, String image) {
        Call<ResimEklePojoSinifi> x = getRestApi().resimYukle(uye_id, ilan_id, image);
        return x;
    }

    public Call<List<IlanlarimPojoSinifi>> ilanlarim(String uyeid) {
        Call<List<IlanlarimPojoSinifi>> x = getRestApi().ilanlarim(uyeid);
        return x;
    }

    public Call<IlanSilmePojoSinifi> ilanlarimSil(String ilanid) {
        Call<IlanSilmePojoSinifi> x = getRestApi().ilanlarimSil(ilanid);
        return x;
    }

    public Call<List<IlanlarPojoSinifi>> ilanlar() {
        Call<List<IlanlarPojoSinifi>> x = getRestApi().ilanlar();
        return x;
    }


    public Call<IlanDetayPojoSinifi> ilanDetay(String ilanid) {
        Call<IlanDetayPojoSinifi> x = getRestApi().ilanDetay(ilanid);
        return x;
    }

    public Call<List<SliderPojoSinifi>> ilanResimleri(String ilanid) {
        Call<List<SliderPojoSinifi>> x = getRestApi().ilanResimleri(ilanid);
        return x;
    }


    public Call<FavoriKontrolPojoSinifi> getButonText(String uyeid, String ilanid) {
        Call<FavoriKontrolPojoSinifi> x = getRestApi().getButonText(uyeid, ilanid);
        return x;
    }

    public Call<FavoriIslemPojoSinifi> favoriIslem(String uyeid, String ilanid) {
        Call<FavoriIslemPojoSinifi> x = getRestApi().favoriIslem(uyeid, ilanid);
        return x;
    }

    public Call<List<FavoriIlanSliderPojoSinifi>> setSlider(String uyeid) {
        Call<List<FavoriIlanSliderPojoSinifi>> x = getRestApi().setSlider(uyeid);
        return x;
    }

    public Call<UserPojoSinifi> getInformation(String uyeid) {
        Call<UserPojoSinifi> x = getRestApi().getInformation(uyeid);
        return x;
    }


    public Call<GuncellePojoSinifi> chandeInformation(String uyeid, String user, String pass) {
        Call<GuncellePojoSinifi> x = getRestApi().chandeInformation(uyeid, user, pass);
        return x;
    }
}
