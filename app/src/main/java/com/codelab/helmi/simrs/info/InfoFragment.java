package com.codelab.helmi.simrs.info;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codelab.helmi.simrs.HomeActivity;
import com.codelab.helmi.simrs.R;
import com.codelab.helmi.simrs.api.RestApi;
import com.codelab.helmi.simrs.api.RestServer;
import com.codelab.helmi.simrs.api.SharedPrefManager;
import com.codelab.helmi.simrs.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment implements View.OnClickListener {
    ImageView ivInfo;
    View view;
    SharedPrefManager sharedPrefManager;
    TextView tvNoRM;
    LinearLayout lnFb, lnIg;
    Button btnLogout, btnGantiPassword;
    RestApi api = RestServer.getClient().create(RestApi.class);

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

        btnLogout = view.findViewById(R.id.btn_logout);
        btnGantiPassword = view.findViewById(R.id.btn_ganti_password);
        btnGantiPassword.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
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
        switch (view.getId()) {
            case R.id.ln_fb:
                Intent facebookIntent = getOpenFacebookIntent(this.getContext());
                startActivity(facebookIntent);
                break;
            case R.id.ln_ig:
                Intent instagramIntent = getOpenInstagramIntent(this.getContext());
                startActivity(instagramIntent);
                break;
            case R.id.btn_logout:
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                Toast.makeText(getActivity(), "Berhasil Logout !", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case R.id.btn_ganti_password:
                showAddItemDialog(getActivity());
                break;
        }
    }

    private void showAddItemDialog(Context c) {
        LinearLayout layout = new LinearLayout(c);
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(30, 20, 30, 0);

        final EditText passwordLama = new EditText(c);
        passwordLama.setHint("Password Lama");
        layout.addView(passwordLama,layoutParams);

        final EditText passwordBaru = new EditText(c);
        passwordBaru.setHint("Password Baru");
        layout.addView(passwordBaru,layoutParams);

        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Ganti Password")
                .setMessage("Silahkan Masukkan Password Lama dan Baru")
                .setView(layout)
                .setPositiveButton("Ganti", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call<GantiPasswordLoginResponseModel> gantiPasswordLoginResponseModelCall = api.gantiPasswordLogin(sharedPrefManager.getSpNoRm(), passwordLama.getText().toString(), passwordBaru.getText().toString());
                        gantiPasswordLoginResponseModelCall.enqueue(new Callback<GantiPasswordLoginResponseModel>() {
                            @Override
                            public void onResponse(Call<GantiPasswordLoginResponseModel> call, Response<GantiPasswordLoginResponseModel> response) {
                                try {
                                    Toast.makeText(getActivity(), response.body().getResult(), Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onFailure(Call<GantiPasswordLoginResponseModel> call, Throwable t) {

                            }
                        });
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
}
