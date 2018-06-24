package com.codelab.helmi.simrs.pesan;

import com.google.gson.annotations.SerializedName;

public class PoliData {
    @SerializedName("id_poli")
    private String id_poli;
    @SerializedName("nama_poli")
    private String nama_poli;

    public String getId_poli() {
        return id_poli;
    }

    public void setId_poli(String id_poli) {
        this.id_poli = id_poli;
    }

    public String getNama_poli() {
        return nama_poli;
    }

    public void setNama_poli(String nama_poli) {
        this.nama_poli = nama_poli;
    }
}
