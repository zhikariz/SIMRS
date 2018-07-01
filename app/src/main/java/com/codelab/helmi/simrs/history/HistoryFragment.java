package com.codelab.helmi.simrs.history;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codelab.helmi.simrs.R;
import com.codelab.helmi.simrs.api.RestApi;
import com.codelab.helmi.simrs.api.RestServer;
import com.codelab.helmi.simrs.api.SharedPrefManager;
import com.codelab.helmi.simrs.jadwal_dokter.JadwalDokterModel;
import com.codelab.helmi.simrs.jadwal_dokter.JadwalDokterRecyclerAdapter;
import com.codelab.helmi.simrs.jadwal_dokter.JadwalDokterResponseModel;
import com.codelab.helmi.simrs.pesan.PesanResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    View view;
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<HistoryData> mItems = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;
    private static Bundle mBundleRecyclerViewState;
    private final String KEY_RECYCLER_STATE = "recycler_state";

    SharedPrefManager sharedPrefManager;



    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.recycler_content, container, false);
        sharedPrefManager = new SharedPrefManager(getActivity().getApplicationContext());
        initView();
        loadData();

        return view;

    }

    private void loadData() {
        RestApi api = RestServer.getClient().create(RestApi.class);
        Call<HistoryResponseModel> getData = api.getPesanOnline(sharedPrefManager.getSpNoRm());
        getData.enqueue(new Callback<HistoryResponseModel>() {
            @Override
            public void onResponse(Call<HistoryResponseModel> call, Response<HistoryResponseModel> response) {
                try {
                    mItems = response.body().getResult();
                    mAdapter = new HistoryRecyclerAdapter(mItems,getActivity().getApplicationContext(),getFragmentManager());
                    mRecycler.setAdapter(mAdapter);
                } catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<HistoryResponseModel> call, Throwable t) {

            }
        });
    }

    private void initView() {
        mRecycler = view.findViewById(R.id.recyclerTemp);
        mManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecycler.setLayoutManager(mManager);
    }

}
