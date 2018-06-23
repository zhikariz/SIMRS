package com.codelab.helmi.simrs.jadwal_dokter;

import java.util.List;
@SuppressWarnings("unused")
public class JadwalDokterResponseModel {
    List<JadwalDokterModel> result;

    public List<JadwalDokterModel> getResult() {
        return result;
    }

    public void setResult(List<JadwalDokterModel> result) {
        this.result = result;
    }
}
