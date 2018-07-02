package com.codelab.helmi.simrs.info;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codelab.helmi.simrs.R;
import com.codelab.helmi.simrs.api.SharedPrefManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment implements View.OnClickListener {
    ImageView ivInfo;
    View view;
    SharedPrefManager sharedPrefManager;
    TextView tvNoRM;
    LinearLayout lnFb, lnIg;
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
        initView();

        return view;
    }

    private void initView() {
        lnFb = view.findViewById(R.id.ln_fb);
        lnFb.setOnClickListener(this);

        lnIg = view.findViewById(R.id.ln_ig);
        lnIg.setOnClickListener(this);
    }

    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://facewebmodal/f?href=https://www.facebook.com/rskasihibusolo/")); //Trys to make intent with FB's URI
//                    Uri.parse("https://www.facebook.com/rskasihibusolo/"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/rskasihibusolo/")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenInstagramIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.instagram.android", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/_u/rskasihibu_solo/")); //Trys to make intent with FB's URI
//                    Uri.parse("https://www.facebook.com/rskasihibusolo/"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/rskasihibu_solo/")); //catches and opens a url to the desired page
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ln_fb:
                Intent facebookIntent = getOpenFacebookIntent(this.getContext());
                startActivity(facebookIntent);
                break;
            case R.id.ln_ig:
                Intent instagramIntent = getOpenInstagramIntent(this.getContext());
                startActivity(instagramIntent);
                break;
        }
    }
}
