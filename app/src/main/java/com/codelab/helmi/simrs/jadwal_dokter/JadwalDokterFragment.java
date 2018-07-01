package com.codelab.helmi.simrs.jadwal_dokter;


import android.os.Bundle;
import android.os.Parcelable;
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
public class JadwalDokterFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    View view;
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<JadwalDokterModel> mItems = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private static Bundle mBundleRecyclerViewState;
    private final String KEY_RECYCLER_STATE = "recycler_state";

    public JadwalDokterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.recycler_content, container, false);
        initView();
        return view;
    }

    public void initView(){
        mRecycler = view.findViewById(R.id.recyclerTemp);
        mManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecycler.setLayoutManager(mManager);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        mRecycler.removeAllViewsInLayout();
        loadData();

    }

    @Override
    public void onPause() {
        super.onPause();
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = mRecycler.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
        mAdapter = mRecycler.getAdapter();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBundleRecyclerViewState = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mBundleRecyclerViewState != null){
            Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            mRecycler.getLayoutManager().onRestoreInstanceState(listState);
            mRecycler.setAdapter(mAdapter);
        }else if(mBundleRecyclerViewState == null){
            swipeRefreshLayout.setRefreshing(true);
            loadData();
        }
    }

    public void loadData() {
        RestApi api = RestServer.getClient().create(RestApi.class);
        Call<JadwalDokterResponseModel> getData = api.getJadwalDokter();
        getData.enqueue(new Callback<JadwalDokterResponseModel>() {
            @Override
            public void onResponse(Call<JadwalDokterResponseModel> call, Response<JadwalDokterResponseModel> response) {
                try {
                    mItems = response.body().getResult();

                    mAdapter = new JadwalDokterRecyclerAdapter(getActivity().getApplicationContext(), mItems,getFragmentManager());
                    mRecycler.setAdapter(mAdapter);
                    swipeRefreshLayout.setRefreshing(false);
                } catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JadwalDokterResponseModel> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }
}
