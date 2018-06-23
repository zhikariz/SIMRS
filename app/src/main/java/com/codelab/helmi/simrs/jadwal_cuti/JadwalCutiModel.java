package com.codelab.helmi.simrs.jadwal_cuti;

import com.google.gson.annotations.SerializedName;
@SuppressWarnings("unused")
public class JadwalCutiModel {
    @SerializedName("nama")
    private String nama;
    @SerializedName("spesialisasi")
    private String spesialisasi;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("keterangan")
    private String keterangan;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
