package com.codelab.helmi.simrs.history;

import com.google.gson.annotations.SerializedName;

public class HistoryData {
    @SerializedName("id_registrasi_periksa")
    private String id_registrasi_periksa;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("tanggal_pesan")
    private String tanggal_pesan;
    @SerializedName("create_at")
    private String create_at;
    @SerializedName("no_rm")
    private String no_rm;
    @SerializedName("status_persetujuan")
    private String status_persetujuan;
    @SerializedName("id_poli_dokter")
    private String id_poli_dokter;
    @SerializedName("id_asuransi")
    private String id_asuransi;
    @SerializedName("nama_asuransi")
    private String nama_asuransi;
    @SerializedName("id_dokter")
    private String id_dokter;
    @SerializedName("id_poli")
    private String id_poli;
    @SerializedName("nama_dokter")
    private String nama_dokter;
    @SerializedName("nama_poli")
    private String nama_poli;

    public String getId_registrasi_periksa() {
        return id_registrasi_periksa;
    }

    public void setId_registrasi_periksa(String id_registrasi_periksa) {
        this.id_registrasi_periksa = id_registrasi_periksa;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getTanggal_pesan() {
        return tanggal_pesan;
    }

    public void setTanggal_pesan(String tanggal_pesan) {
        this.tanggal_pesan = tanggal_pesan;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getNo_rm() {
        return no_rm;
    }

    public void setNo_rm(String no_rm) {
        this.no_rm = no_rm;
    }

    public String getStatus_persetujuan() {
        return status_persetujuan;
    }

    public void setStatus_persetujuan(String status_persetujuan) {
        this.status_persetujuan = status_persetujuan;
    }

    public String getId_poli_dokter() {
        return id_poli_dokter;
    }

    public void setId_poli_dokter(String id_poli_dokter) {
        this.id_poli_dokter = id_poli_dokter;
    }

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
