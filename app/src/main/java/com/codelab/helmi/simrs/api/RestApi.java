package com.codelab.helmi.simrs.api;

import com.codelab.helmi.simrs.chat.FirebaseMessage;
import com.codelab.helmi.simrs.history.HistoryResponseModel;
import com.codelab.helmi.simrs.info.GantiPasswordLoginResponseModel;
import com.codelab.helmi.simrs.jadwal_cuti.JadwalCutiResponseModel;
import com.codelab.helmi.simrs.jadwal_dokter.JadwalDokterResponseModel;
import com.codelab.helmi.simrs.login.GantiPasswordBelumLoginResponseModel;
import com.codelab.helmi.simrs.login.LoginResponseModel;
import com.codelab.helmi.simrs.pesan.PesanResponseModel;
import com.codelab.helmi.simrs.register.RegisterResponseModel;
import com.codelab.helmi.simrs.pesan.AsuransiResponseModel;
import com.codelab.helmi.simrs.pesan.PoliDokterResponseModel;
import com.codelab.helmi.simrs.pesan.PoliResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {

    //firebase
    @Headers({"Authorization:key=AAAAs9YOaXQ:APA91bE2rNdK_Kz7l7KKOIpEP6inWKzfNYfgCpsy4MKZRZoU1NZS3Y-6jNY13kIS0npxuwUJzyAQSVViml8vb79hQHRjd7AKooU460umi1X1NeDka2Fj8eEKHyyL1Hks5IxkCvFCMKioKg4D1RLF6nUac-BTdXTTag",
            "Content-Type:application/json"})
    @POST("fcm/send")
    Call<FirebaseMessage> sendMessage(@Body FirebaseMessage message);

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

    @POST("pesan_online.php")
    @FormUrlEncoded
    Call<PesanResponseModel> postDataPesan(@Field("kategori") String kategori,
                                           @Field("tanggal_pesan") String tanggal_pesan,
                                           @Field("no_rm") String no_rm,
                                           @Field("status_persetujuan") String status_persetujuan,
                                           @Field("id_poli_dokter") String id_poli_dokter,
                                           @Field("id_asuransi") String id_asuransi);
    @GET("tampil_riwayat_pesan_online.php")
    Call<HistoryResponseModel> getPesanOnline(@Query("no_rm") String no_rm);

    @POST("ganti_password_sudah_login.php")
    @FormUrlEncoded
    Call<GantiPasswordLoginResponseModel> gantiPasswordLogin(@Field("no_rm") String no_rm,
                                                             @Field("password_lama") String password_lama,
                                                             @Field("password_baru") String password_baru);

    @POST("ganti_password_blm_login.php")
    @FormUrlEncoded
    Call<GantiPasswordBelumLoginResponseModel> gantiPasswordBelumLogin(@Field("no_rm") String no_rm,
                                                                  @Field("password_baru") String password_baru);

}
