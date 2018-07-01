package com.codelab.helmi.simrs.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codelab.helmi.simrs.jadwal_cuti.JadwalCutiFragment;
import com.codelab.helmi.simrs.jadwal_dokter.JadwalDokterFragment;
import com.codelab.helmi.simrs.layanan.LayananFragment;
import com.codelab.helmi.simrs.pesan.PesanFragment;
import com.codelab.helmi.simrs.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    FragmentManager fragmentManager;
    Fragment fragment;
    ImageView ivLayanan, ivJadwalCuti, ivJadwalDokter, ivPesan;
    View view;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();

        return view;
    }

    private void initView() {
        ivLayanan = view.findViewById(R.id.iv_layanan);
        Glide.with(this).load(R.drawable.ic_layanan).into(ivLayanan);
        ivLayanan.setOnClickListener(this);

        ivJadwalCuti = view.findViewById(R.id.iv_jadwal_cuti);
        Glide.with(this).load(R.drawable.ic_cuti).into(ivJadwalCuti);
        ivJadwalCuti.setOnClickListener(this);

        ivJadwalDokter = view.findViewById(R.id.iv_jadwal_dokter);
        Glide.with(this).load(R.drawable.ic_jadwal_dokter).into(ivJadwalDokter);
        ivJadwalDokter.setOnClickListener(this);

        ivPesan = view.findViewById(R.id.iv_pesan);
        ivPesan.setOnClickListener(this);
        Glide.with(this).load(R.drawable.ic_pesan).into(ivPesan);
    }

    private void callFragment(Fragment fragment){
        fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_layanan:
                fragment =  new LayananFragment();
                callFragment(fragment);
                break;

            case R.id.iv_jadwal_cuti:
                fragment = new JadwalCutiFragment();
                callFragment(fragment);
                break;

            case R.id.iv_jadwal_dokter:
                fragment = new JadwalDokterFragment();
                callFragment(fragment);
                break;

            case R.id.iv_pesan:
                fragment = new PesanFragment();
                callFragment(fragment);
                break;
        }
    }
}
