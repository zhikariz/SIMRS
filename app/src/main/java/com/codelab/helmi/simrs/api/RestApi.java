package com.codelab.helmi.simrs.api;

import com.codelab.helmi.simrs.jadwal_cuti.JadwalCutiResponseModel;
import com.codelab.helmi.simrs.jadwal_dokter.JadwalDokterResponseModel;
import com.codelab.helmi.simrs.login.LoginResponseModel;
import com.codelab.helmi.simrs.register.RegisterResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
}
