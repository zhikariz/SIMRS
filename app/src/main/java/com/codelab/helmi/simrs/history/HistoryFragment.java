package com.codelab.helmi.simrs.history;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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
public class HistoryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, SearchView.OnQueryTextListener {
    View view;
    private RecyclerView mRecycler;
    private HistoryRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<HistoryData> mItems = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;
    private static Bundle mBundleRecyclerViewState;
    private final String KEY_RECYCLER_STATE = "recycler_state";

    SearchView searchView;

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
                    mAdapter = new HistoryRecyclerAdapter(mItems, getActivity().getApplicationContext(), getFragmentManager());
                    mRecycler.setAdapter(mAdapter);
                    swipeRefreshLayout.setRefreshing(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<HistoryResponseModel> call, Throwable t) {
                t.printStackTrace();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initView() {
        mRecycler = view.findViewById(R.id.recyclerTemp);
        mManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecycler.setLayoutManager(mManager);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        searchView = view.findViewById(R.id.search_view);
        searchView.setOnClickListener(this);
        searchView.setOnQueryTextListener(this);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBundleRecyclerViewState = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_view:
                searchView.setIconified(false);
                break;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mAdapter.getFilter().filter(newText);
        return true;
    }
}
