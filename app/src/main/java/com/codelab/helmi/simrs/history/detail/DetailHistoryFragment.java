package com.codelab.helmi.simrs.history.detail;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codelab.helmi.simrs.R;
import com.codelab.helmi.simrs.history.HistoryData;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailHistoryFragment extends Fragment {

    public static String EXTRA_HISTORY = "extra_history";
    View view;
    TextView tvNoRm, tvTglPesan, tvNamaPoli, tvNamaDokter, tvKategori, tvAsuransi, tvStatus;
    Bundle bundle;
    HistoryData historyData;


    public DetailHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_detail_history, container, false);
        initView();
        tampilDataDetail();
        return view;
    }

    private void initView() {
        tvNoRm = view.findViewById(R.id.tv_detail_history_no_rm);
        tvTglPesan = view.findViewById(R.id.tv_detail_history_tgl_pesan);
        tvNamaPoli = view.findViewById(R.id.tv_detail_history_nama_poli);
        tvNamaDokter = view.findViewById(R.id.tv_detail_history_nama_dokter);
        tvKategori = view.findViewById(R.id.tv_detail_history_kategori);
        tvAsuransi = view.findViewById(R.id.tv_detail_history_nama_asuransi);
        tvStatus = view.findViewById(R.id.tv_detail_history_status);
    }

    private void tampilDataDetail() {
        bundle = this.getArguments();
        historyData = bundle.getParcelable(EXTRA_HISTORY);
        tvNoRm.setText(historyData.getNo_rm());
        tvTglPesan.setText(historyData.getTanggal_pesan());
        tvNamaPoli.setText(historyData.getNama_poli());
        tvNamaDokter.setText(historyData.getNama_dokter());
        tvKategori.setText(historyData.getKategori());
        tvAsuransi.setText(historyData.getNama_asuransi());
        tvStatus.setText(historyData.getStatus_persetujuan());
        if(historyData.getStatus_persetujuan().equals("Belum disetujui")){
            tvStatus.setTextColor(Color.parseColor("#ff0000"));
        }else{
            tvStatus.setTextColor(Color.parseColor("008000"));
        }

    }

}
