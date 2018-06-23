package com.codelab.helmi.simrs.api;

import com.codelab.helmi.simrs.jadwal_cuti.JadwalCutiResponseModel;
import com.codelab.helmi.simrs.jadwal_dokter.JadwalDokterResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    //read
    @GET("jadwal_dokter.php")
    Call<JadwalDokterResponseModel> getJadwalDokter();
    @GET("jadwal_cuti.php")
    Call<JadwalCutiResponseModel> getJadwalCuti();
}
