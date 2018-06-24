package com.codelab.helmi.simrs.pesan;

import com.google.gson.annotations.SerializedName;

public class PoliDokterData {
    @SerializedName("id_poli_dokter")
    private String id_poli_dokter;
    @SerializedName("id_dokter")
    private String id_dokter;
    @SerializedName("id_poli")
    private String id_poli;
    @SerializedName("nama_dokter")
    private String nama_dokter;
    @SerializedName("nama_poli")
    private String nama_poli;

    public String getId_poli_dokter() {
        return id_poli_dokter;
    }

    public void setId_poli_dokter(String id_poli_dokter) {
        this.id_poli_dokter = id_poli_dokter;
    }

    public String getId_dokter() {
        return id_dokter;
    }

    public void setId_dokter(String id_dokter) {
        this.id_dokter = id_dokter;
    }

    public String getId_poli() {
        return id_poli;
    }

    public void setId_poli(String id_poli) {
        this.id_poli = id_poli;
    }

    public String getNama_dokter() {
        return nama_dokter;
    }

    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    public String getNama_poli() {
        return nama_poli;
    }

    public void setNama_poli(String nama_poli) {
        this.nama_poli = nama_poli;
    }
}
