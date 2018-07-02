package com.codelab.helmi.simrs.layanan;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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
public class LayananFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    ImageView ivLayanan;
    View view;
    private SliderLayout mSlider;
    ArrayList<String> listUrl = new ArrayList<>();
    ArrayList<String> listName = new ArrayList<>();
    RequestOptions requestOptions = new RequestOptions();
    public LayananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_layanan, container, false);
        mSlider = view.findViewById(R.id.slider_layanan);
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
        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/1.png");
        listName.add("1");

        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/2.png");
        listName.add("2");

        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/3.png");
        listName.add("3");

        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/4.png");
        listName.add("4");

        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/5.png");
        listName.add("5");

        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/6.png");
        listName.add("6");

        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/7.png");
        listName.add("7");

        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/8.png");
        listName.add("8");

        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/9.png");
        listName.add("9");

        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/10.png");
        listName.add("10");

        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/11.png");
        listName.add("11");

        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/12.png");
        listName.add("12");

        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/13.png");
        listName.add("13");

        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/14.png");
        listName.add("14");

        listUrl.add("http://rskasihibu.com/simrs/gambar_layanan/15.png");
        listName.add("15");


//        requestOptions.centerCrop();
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
}
