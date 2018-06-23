package com.codelab.helmi.simrs.jadwal_dokter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codelab.helmi.simrs.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalDokterFragment extends Fragment {


    public JadwalDokterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal_dokter, container, false);
    }

}
