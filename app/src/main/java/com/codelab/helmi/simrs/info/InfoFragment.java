package com.codelab.helmi.simrs.info;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codelab.helmi.simrs.R;
import com.codelab.helmi.simrs.api.SharedPrefManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {
    ImageView ivInfo;
    View view;
    SharedPrefManager sharedPrefManager;
    TextView tvNoRM;
    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_info, container, false);
        ivInfo = (ImageView) view.findViewById(R.id.iv_info);
        Glide.with(this).load(R.drawable.rsu_kasih_ibu).into(ivInfo);
        sharedPrefManager = new SharedPrefManager(getActivity());
        tvNoRM = view.findViewById(R.id.no_rm_login);
        tvNoRM.setText(sharedPrefManager.getSpNoRm());
        return view;
    }

}
