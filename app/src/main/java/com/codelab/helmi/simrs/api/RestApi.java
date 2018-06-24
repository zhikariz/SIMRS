package com.codelab.helmi.simrs.api;

import com.codelab.helmi.simrs.jadwal_cuti.JadwalCutiResponseModel;
import com.codelab.helmi.simrs.jadwal_dokter.JadwalDokterResponseModel;
import com.codelab.helmi.simrs.login.LoginResponseModel;
import com.codelab.helmi.simrs.register.RegisterResponseModel;
import com.codelab.helmi.simrs.pesan.AsuransiResponseModel;
import com.codelab.helmi.simrs.pesan.PoliDokterResponseModel;
import com.codelab.helmi.simrs.pesan.PoliResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {

    //read
    @GET("jadwal_dokter.php")
    Call<JadwalDokterResponseModel> getJadwalDokter();
    @GET("jadwal_cuti.php")
    Call<JadwalCutiResponseModel> getJadwalCuti();

    @POST("register_user.php")
    @FormUrlEncoded
    Call<RegisterResponseModel> registerUser(@Field("no_rm") String no_rm, @Field("password") String password, @Field("password2") String password2);

    @POST("login_user.php")
    @FormUrlEncoded
    Call<LoginResponseModel> loginUser(@Field("no_rm") String no_rm, @Field("password") String password);
    @GET("tampil_poli.php")
    Call<PoliResponseModel> getPoli();
    @GET("tampil_poli_dokter.php")
    Call<PoliDokterResponseModel> getPoliDokter(@Query("id_poli") String id_poli);
    @GET("tampil_asuransi.php")
    Call<AsuransiResponseModel> getAsuransi();
}
