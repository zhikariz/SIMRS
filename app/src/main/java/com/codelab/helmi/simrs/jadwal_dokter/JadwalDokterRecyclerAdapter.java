package com.codelab.helmi.simrs.jadwal_dokter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codelab.helmi.simrs.R;
import com.codelab.helmi.simrs.jadwal_dokter.detail.DetailJadwalDokterFragment;

import java.util.List;

public class JadwalDokterRecyclerAdapter extends RecyclerView.Adapter<JadwalDokterRecyclerAdapter.MyHolder> {

    List<JadwalDokterModel> mList;
    Context ctx;
    FragmentManager fragmentManager;


    public JadwalDokterRecyclerAdapter(Context ctx, List<JadwalDokterModel> mList, FragmentManager fragmentManager) {
        this.mList = mList;
        this.ctx = ctx;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_jadwal_dokter, parent, false);
        MyHolder holder = new MyHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.nama.setText(mList.get(position).getDokter());
        holder.spesialis.setText(mList.get(position).getSpesialis());
        Glide.with(this.ctx).load(mList.get(position).getGambar()).into(holder.ivDokter);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailJadwalDokterFragment detailJadwalDokterFragment = new DetailJadwalDokterFragment();
                JadwalDokterModel jadwalDokterModel = new JadwalDokterModel();

                jadwalDokterModel.setDokter(mList.get(position).getDokter());
                jadwalDokterModel.setSpesialis(mList.get(position).getSpesialis());
                jadwalDokterModel.setSenin(mList.get(position).getSenin());
                jadwalDokterModel.setSelasa(mList.get(position).getSelasa());
                jadwalDokterModel.setRabu(mList.get(position).getRabu());
                jadwalDokterModel.setKamis(mList.get(position).getKamis());
                jadwalDokterModel.setJumat(mList.get(position).getJumat());
                jadwalDokterModel.setSabtu(mList.get(position).getSabtu());
                jadwalDokterModel.setMinggu(mList.get(position).getMinggu());

                Bundle bundle = new Bundle();
                bundle.putParcelable(DetailJadwalDokterFragment.EXTRA_JADWAL_DOKTER, jadwalDokterModel);
                detailJadwalDokterFragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, detailJadwalDokterFragment, detailJadwalDokterFragment.getClass().getSimpleName())
                        .addToBackStack(detailJadwalDokterFragment.getClass().getSimpleName())
                        .commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView nama, spesialis, usia;
        ImageView ivDokter;

        public MyHolder(View v) {
            super(v);

            nama = (TextView) v.findViewById(R.id.tv_jadwal_dokter_nama);
            spesialis = (TextView) v.findViewById(R.id.tv_jadwal_dokter_spesialis);
            ivDokter = (ImageView) v.findViewById(R.id.iv_dokter);


        }

    }
}
