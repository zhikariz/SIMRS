package com.codelab.helmi.simrs.history;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class HistoryData implements Parcelable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id_registrasi_periksa);
        dest.writeString(this.kategori);
        dest.writeString(this.tanggal_pesan);
        dest.writeString(this.create_at);
        dest.writeString(this.no_rm);
        dest.writeString(this.status_persetujuan);
        dest.writeString(this.id_poli_dokter);
        dest.writeString(this.id_asuransi);
        dest.writeString(this.nama_asuransi);
        dest.writeString(this.id_dokter);
        dest.writeString(this.id_poli);
        dest.writeString(this.nama_dokter);
        dest.writeString(this.nama_poli);
    }

    public HistoryData() {
    }

    protected HistoryData(Parcel in) {
        this.id_registrasi_periksa = in.readString();
        this.kategori = in.readString();
        this.tanggal_pesan = in.readString();
        this.create_at = in.readString();
        this.no_rm = in.readString();
        this.status_persetujuan = in.readString();
        this.id_poli_dokter = in.readString();
        this.id_asuransi = in.readString();
        this.nama_asuransi = in.readString();
        this.id_dokter = in.readString();
        this.id_poli = in.readString();
        this.nama_dokter = in.readString();
        this.nama_poli = in.readString();
    }

    public static final Parcelable.Creator<HistoryData> CREATOR = new Parcelable.Creator<HistoryData>() {
        @Override
        public HistoryData createFromParcel(Parcel source) {
            return new HistoryData(source);
        }

        @Override
        public HistoryData[] newArray(int size) {
            return new HistoryData[size];
        }
    };
}
