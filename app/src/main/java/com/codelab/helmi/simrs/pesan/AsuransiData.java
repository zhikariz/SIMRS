package com.codelab.helmi.simrs.pesan;

import com.google.gson.annotations.SerializedName;

public class AsuransiData {
    @SerializedName("id_asuransi")
    private String id_asuransi;
    @SerializedName("nama_asuransi")
    private String nama_asuransi;

    public String getId_asuransi() {
        return id_asuransi;
    }

    public void setId_asuransi(String id_asuransi) {
        this.id_asuransi = id_asuransi;
    }

    public String getNama_asuransi() {
        return nama_asuransi;
    }

    public void setNama_asuransi(String nama_asuransi) {
        this.nama_asuransi = nama_asuransi;
    }
}
