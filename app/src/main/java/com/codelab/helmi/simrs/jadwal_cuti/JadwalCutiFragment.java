package com.codelab.helmi.simrs.jadwal_cuti;


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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalCutiFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    View view;
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<JadwalCutiModel> mItems = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    public JadwalCutiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.recycler_content, container, false);
        initView();
        loadData();

        return view;
    }

    private void initView() {
        mRecycler = view.findViewById(R.id.recyclerTemp);
        mManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecycler.setLayoutManager(mManager);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        mRecycler.removeAllViewsInLayout();
        loadData();
    }

    public void loadData(){
        RestApi api = RestServer.getClient().create(RestApi.class);
        Call<JadwalCutiResponseModel> getData = api.getJadwalCuti();
        getData.enqueue(new Callback<JadwalCutiResponseModel>() {
            @Override
            public void onResponse(Call<JadwalCutiResponseModel> call, Response<JadwalCutiResponseModel> response) {
                mItems = response.body().getResult();

                mAdapter = new JadwalCutiRecyclerAdapter(getActivity().getApplicationContext(), mItems);
                mRecycler.setAdapter(mAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<JadwalCutiResponseModel> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

}
