package com.codelab.helmi.simrs.history;

import com.codelab.helmi.simrs.pesan.AsuransiData;

import java.util.List;

public class HistoryResponseModel {
    List<HistoryData> result;

    public List<HistoryData> getResult() {
        return result;
    }

    public void setResult(List<HistoryData> result) {
        this.result = result;
    }
}
