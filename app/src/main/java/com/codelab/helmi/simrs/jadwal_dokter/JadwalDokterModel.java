package com.codelab.helmi.simrs.jadwal_dokter;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class JadwalDokterModel implements Parcelable {
    @SerializedName("dokter")
    private String dokter;
    @SerializedName("senin")
    private String senin;
    @SerializedName("selasa")
    private String selasa;
    @SerializedName("rabu")
    private String rabu;
    @SerializedName("kamis")
    private String kamis;
    @SerializedName("jumat")
    private String jumat;
    @SerializedName("sabtu")
    private String sabtu;
    @SerializedName("minggu")
    private String minggu;
    @SerializedName("spesialis")
    private String spesialis;


    public String getDokter() {
        return dokter;
    }

    public void setDokter(String dokter) {
        this.dokter = dokter;
    }

    public String getSenin() {
        return senin;
    }

    public void setSenin(String senin) {
        this.senin = senin;
    }

    public String getSelasa() {
        return selasa;
    }

    public void setSelasa(String selasa) {
        this.selasa = selasa;
    }

    public String getRabu() {
        return rabu;
    }

    public void setRabu(String rabu) {
        this.rabu = rabu;
    }

    public String getKamis() {
        return kamis;
    }

    public void setKamis(String kamis) {
        this.kamis = kamis;
    }

    public String getJumat() {
        return jumat;
    }

    public void setJumat(String jumat) {
        this.jumat = jumat;
    }

    public String getSabtu() {
        return sabtu;
    }

    public void setSabtu(String sabtu) {
        this.sabtu = sabtu;
    }

    public String getMinggu() {
        return minggu;
    }

    public void setMinggu(String minggu) {
        this.minggu = minggu;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dokter);
        dest.writeString(this.senin);
        dest.writeString(this.selasa);
        dest.writeString(this.rabu);
        dest.writeString(this.kamis);
        dest.writeString(this.jumat);
        dest.writeString(this.sabtu);
        dest.writeString(this.minggu);
        dest.writeString(this.spesialis);
    }

    public JadwalDokterModel() {
    }

    protected JadwalDokterModel(Parcel in) {
        this.dokter = in.readString();
        this.senin = in.readString();
        this.selasa = in.readString();
        this.rabu = in.readString();
        this.kamis = in.readString();
        this.jumat = in.readString();
        this.sabtu = in.readString();
        this.minggu = in.readString();
        this.spesialis = in.readString();
    }

    public static final Parcelable.Creator<JadwalDokterModel> CREATOR = new Parcelable.Creator<JadwalDokterModel>() {
        @Override
        public JadwalDokterModel createFromParcel(Parcel source) {
            return new JadwalDokterModel(source);
        }

        @Override
        public JadwalDokterModel[] newArray(int size) {
            return new JadwalDokterModel[size];
        }
    };
}
