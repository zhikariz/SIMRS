package com.codelab.helmi.simrs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


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
        ivLayanan.setOnClickListener(this);
        ivJadwalCuti = view.findViewById(R.id.iv_jadwal_cuti);
        ivJadwalCuti.setOnClickListener(this);
        ivJadwalDokter = view.findViewById(R.id.iv_jadwal_dokter);
        ivJadwalDokter.setOnClickListener(this);
        ivPesan = view.findViewById(R.id.iv_pesan);
        ivPesan.setOnClickListener(this);
    }

    private void callFragment(Fragment fragment){
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
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
