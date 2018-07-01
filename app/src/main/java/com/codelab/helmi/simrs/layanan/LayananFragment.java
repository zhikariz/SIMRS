package com.codelab.helmi.simrs.layanan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codelab.helmi.simrs.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LayananFragment extends Fragment {

    ImageView ivLayanan;
    View view;
    public LayananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_layanan, container, false);
        ivLayanan = view.findViewById(R.id.layanan_gambar);
        Glide.with(this).load(R.drawable.layanan1).into(ivLayanan);
        return view;
    }

}
