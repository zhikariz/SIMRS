package com.codelab.helmi.simrs.api;

import com.codelab.helmi.simrs.jadwal_cuti.JadwalCutiResponseModel;
import com.codelab.helmi.simrs.jadwal_dokter.JadwalDokterResponseModel;
import com.codelab.helmi.simrs.pesan.AsuransiResponseModel;
import com.codelab.helmi.simrs.pesan.PoliDokterResponseModel;
import com.codelab.helmi.simrs.pesan.PoliResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    //read
    @GET("jadwal_dokter.php")
    Call<JadwalDokterResponseModel> getJadwalDokter();
    @GET("jadwal_cuti.php")
    Call<JadwalCutiResponseModel> getJadwalCuti();
    @GET("tampil_poli.php")
    Call<PoliResponseModel> getPoli();
    @GET("tampil_poli_dokter.php")
    Call<PoliDokterResponseModel> getPoliDokter(@Query("id_poli") String id_poli);
    @GET("tampil_asuransi.php")
    Call<AsuransiResponseModel> getAsuransi();
}
