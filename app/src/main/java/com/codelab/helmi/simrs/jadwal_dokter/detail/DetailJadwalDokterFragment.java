package com.codelab.helmi.simrs.jadwal_dokter.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codelab.helmi.simrs.R;
import com.codelab.helmi.simrs.jadwal_dokter.JadwalDokterModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailJadwalDokterFragment extends Fragment {

    public static String EXTRA_JADWAL_DOKTER = "extra_jadwal_dokter";
    View view;
    TextView tvNama, tvSpesialis, tvSenin, tvSelasa, tvRabu, tvKamis, tvJumat, tvSabtu, tvMinggu;
    Bundle bundle;
    JadwalDokterModel jadwalDokterModel;


    public DetailJadwalDokterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_detail_jadwal_dokter, container, false);
        initView();
        tampilDataDetail();
        return view;
    }

    public void initView() {
        tvNama = view.findViewById(R.id.tv_detail_jadwal_dokter_nama);
        tvSpesialis = view.findViewById(R.id.tv_detail_jadwal_dokter_spesialis);
        tvSenin = view.findViewById(R.id.tv_detail_jadwal_dokter_senin);
        tvSelasa = view.findViewById(R.id.tv_detail_jadwal_dokter_selasa);
        tvRabu = view.findViewById(R.id.tv_detail_jadwal_dokter_rabu);
        tvKamis = view.findViewById(R.id.tv_detail_jadwal_dokter_kamis);
        tvJumat = view.findViewById(R.id.tv_detail_jadwal_dokter_jumat);
        tvSabtu = view.findViewById(R.id.tv_detail_jadwal_dokter_sabtu);
        tvMinggu = view.findViewById(R.id.tv_detail_jadwal_dokter_minggu);

    }

    public void tampilDataDetail() {
        bundle = this.getArguments();
        jadwalDokterModel = bundle.getParcelable(EXTRA_JADWAL_DOKTER);
        tvNama.setText(jadwalDokterModel.getDokter());
        tvSpesialis.setText(jadwalDokterModel.getSpesialis());
        tvSenin.setText(jadwalDokterModel.getSenin());
        tvSelasa.setText(jadwalDokterModel.getSelasa());
        tvRabu.setText(jadwalDokterModel.getRabu());
        tvKamis.setText(jadwalDokterModel.getKamis());
        tvJumat.setText(jadwalDokterModel.getJumat());
        tvSabtu.setText(jadwalDokterModel.getSabtu());
        tvMinggu.setText(jadwalDokterModel.getMinggu());


    }

}
