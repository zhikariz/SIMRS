package com.codelab.helmi.simrs.pesan;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codelab.helmi.simrs.R;
import com.codelab.helmi.simrs.api.RestApi;
import com.codelab.helmi.simrs.api.RestServer;
import com.codelab.helmi.simrs.api.SharedPrefManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PesanFragment extends Fragment implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener {
    View view;
    Spinner sp_poli, sp_dokter, sp_asuransi;
    String kategori, id_poli_dokter, id_asuransi;
    View vSp;
    EditText edtTglPesan;
    Context context;
    List<PoliData> mItems = new ArrayList<>();
    List<PoliDokterData> mItems2 = new ArrayList<>();
    List<AsuransiData> mItems3 = new ArrayList<>();

    List<String> mList = new ArrayList<String>();
    List<String> mList2 = new ArrayList<String>();
    List<String> mList3 = new ArrayList<String>();

    ArrayAdapter<String> spinnerAdapter2 = null;
    RadioGroup rgKategori;
    TextView tvAsuransi;
    Button btnSubmit;
    RestApi api = RestServer.getClient().create(RestApi.class);
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;

    SharedPrefManager sharedPrefManager;


    public PesanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pesan, container, false);
        sp_poli = view.findViewById(R.id.sp_poli);
        sp_poli.setOnItemSelectedListener(this);
        sp_dokter = view.findViewById(R.id.sp_dokter);
        sp_dokter.setOnItemSelectedListener(this);
        sp_asuransi = view.findViewById(R.id.sp_asuransi);
        sp_asuransi.setOnItemSelectedListener(this);
        context = getActivity().getApplicationContext();
        rgKategori = view.findViewById(R.id.rg_kategori);
        rgKategori.setOnCheckedChangeListener(this);
        tvAsuransi = view.findViewById(R.id.tv_asuransi);
        edtTglPesan = view.findViewById(R.id.edt_tgl_pesan);
        btnSubmit = view.findViewById(R.id.btn_submit_pesan);
        vSp = view.findViewById(R.id.view_sp);

        sharedPrefManager = new SharedPrefManager(getActivity().getApplicationContext());

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        edtTglPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });


        Call<PoliResponseModel> getDataPoli = api.getPoli();
        getDataPoli.enqueue(new Callback<PoliResponseModel>() {
            @Override
            public void onResponse(Call<PoliResponseModel> call, Response<PoliResponseModel> response) {
                try {
                    mItems = response.body().getResult();
                    int jml = mItems.size();

                    for (int i = 0; i < jml; i++) {
                        mList.add(mItems.get(i).getNama_poli());
                    }
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, R.layout.spinner_item, mList);
                    spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    spinnerAdapter.notifyDataSetChanged();
                    sp_poli.setAdapter(spinnerAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<PoliResponseModel> call, Throwable t) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PesanResponseModel> postData = api.postDataPesan(kategori, edtTglPesan.getText().toString(), sharedPrefManager.getSpNoRm(), "Belum disetujui", id_poli_dokter, id_asuransi);
                postData.enqueue(new Callback<PesanResponseModel>() {
                    @Override
                    public void onResponse(Call<PesanResponseModel> call, Response<PesanResponseModel> response) {
                        try {
                            String result = response.body().getResult();
                            if (result.equals("Gagal Pesan! , Form Masih Kosong")){
                                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStackImmediate();
                            }

                        } catch (Exception e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<PesanResponseModel> call, Throwable t) {

                    }
                });
            }
        });


        return view;
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edtTglPesan.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int id_view = parent.getId();

        if (id_view == R.id.sp_poli) {
//            Toast.makeText(
//                    getActivity().getApplicationContext(),
//                    String.valueOf(mItems.get(position).getId_poli()) + " Selected",
//                    Toast.LENGTH_LONG).show();

            Call<PoliDokterResponseModel> getDataDokter = api.getPoliDokter(mItems.get(position).getId_poli());
            getDataDokter.enqueue(new Callback<PoliDokterResponseModel>() {
                @Override
                public void onResponse(Call<PoliDokterResponseModel> call, Response<PoliDokterResponseModel> response2) {
                    try {
                        if (spinnerAdapter2 != null) {

                            spinnerAdapter2.clear();
                            spinnerAdapter2.notifyDataSetChanged();

                        }
                        mItems2 = response2.body().getResult();
                        int jml2 = mItems2.size();
                        for (int i = 0; i < jml2; i++) {
                            mList2.add(mItems2.get(i).getNama_dokter());
                        }


                        spinnerAdapter2 = new ArrayAdapter<String>(context, R.layout.spinner_item, mList2);
                        spinnerAdapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        spinnerAdapter2.notifyDataSetChanged();
                        sp_dokter.setAdapter(spinnerAdapter2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<PoliDokterResponseModel> call, Throwable t) {

                }
            });


        } else if (id_view == R.id.sp_dokter) {
            id_poli_dokter = mItems2.get(position).getId_poli_dokter();
        } else if (id_view == R.id.sp_asuransi) {
            id_asuransi = mItems3.get(position).getId_asuransi();

        }
//        Toast.makeText(
//                getActivity().getApplicationContext(),
//                parent.getItemAtPosition(position).toString() + " Selected" ,
//                Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_pribadi:
                sp_asuransi.setVisibility(View.GONE);
                tvAsuransi.setVisibility(View.GONE);
                vSp.setVisibility(View.GONE);
                kategori = "Pribadi";
                break;
            case R.id.rb_bpjs:
                sp_asuransi.setVisibility(View.GONE);
                tvAsuransi.setVisibility(View.GONE);
                vSp.setVisibility(View.GONE);
                kategori = "Bpjs";
                break;
            case R.id.rb_asuransi:
                sp_asuransi.setVisibility(View.VISIBLE);
                tvAsuransi.setVisibility(View.VISIBLE);
                vSp.setVisibility(View.VISIBLE);
                kategori = "Asuransi";
                Call<AsuransiResponseModel> getDataAsuransi = api.getAsuransi();
                getDataAsuransi.enqueue(new Callback<AsuransiResponseModel>() {
                    @Override
                    public void onResponse(Call<AsuransiResponseModel> call, Response<AsuransiResponseModel> response) {
                        try {
                            mItems3 = response.body().getResult();
                            int jml3 = mItems3.size();
                            for (int i = 0; i < jml3; i++) {
                                mList3.add(mItems3.get(i).getNama_asuransi());
                            }
                            ArrayAdapter<String> spinnerAdapter3 = new ArrayAdapter<String>(context, R.layout.spinner_item, mList3);
                            spinnerAdapter3.setDropDownViewResource(R.layout.spinner_dropdown_item);
                            spinnerAdapter3.notifyDataSetChanged();
                            sp_asuransi.setAdapter(spinnerAdapter3);

                        } catch (Exception e) {
            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<AsuransiResponseModel> call, Throwable t) {

                    }
                });
                break;
        }
    }
}
