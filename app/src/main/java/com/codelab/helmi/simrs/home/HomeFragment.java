package com.codelab.helmi.simrs.home;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codelab.helmi.simrs.jadwal_cuti.JadwalCutiFragment;
import com.codelab.helmi.simrs.jadwal_dokter.JadwalDokterFragment;
import com.codelab.helmi.simrs.layanan.LayananFragment;
import com.codelab.helmi.simrs.pesan.PesanFragment;
import com.codelab.helmi.simrs.R;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.TextSliderView;
import com.glide.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    FragmentManager fragmentManager;
    Fragment fragment;
    ImageView ivLayanan, ivJadwalCuti, ivJadwalDokter, ivPesan;
    View view;
    private SliderLayout mSlider;
    ArrayList<String> listUrl = new ArrayList<>();
    ArrayList<String> listName = new ArrayList<>();
    RequestOptions requestOptions = new RequestOptions();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        initSlider();
        implSlider();

        return view;
    }

    private void implSlider() {

        for (int i = 0; i < listUrl.size(); i++) {
            TextSliderView sliderView = new TextSliderView(getActivity().getApplicationContext());
            sliderView
                    .image(listUrl.get(i))
                    .description(listName.get(i))
                    .setRequestOption(requestOptions)
                    .setBackgroundColor(Color.WHITE)
                    .setProgressBarVisible(true)
                    .setOnSliderClickListener(this);

            sliderView.bundle(new Bundle());
            sliderView.getBundle().putString("extra", listName.get(i));
            mSlider.addSlider(sliderView);

        }
        mSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setDuration(4000);
        mSlider.addOnPageChangeListener(this);

    }

    private void initSlider() {
        listUrl.add("http://rskasihibu.com/wp-content/uploads/2017/10/man-slider-kecil-1.jpg");
        listName.add("Manajemen");

        listUrl.add("http://rskasihibu.com/wp-content/uploads/2018/03/PhotoGrid_1521684647813.jpg");
        listName.add("Mutu Pelayanan");

        listUrl.add("http://rskasihibu.com/wp-content/uploads/2018/04/WhatsApp-Image-2018-04-19-at-07.50.53.jpeg");
        listName.add("Inovasi");

        listUrl.add("http://rskasihibu.com/wp-content/uploads/2018/02/PhotoGrid_1519633157048.jpg");
        listName.add("Pelayanan");

        requestOptions.centerCrop();
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

        mSlider = view.findViewById(R.id.slider);
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

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {
        Toast.makeText(getActivity(), baseSliderView.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onStop() {
        super.onStop();
        mSlider.stopAutoCycle();
    }
}
