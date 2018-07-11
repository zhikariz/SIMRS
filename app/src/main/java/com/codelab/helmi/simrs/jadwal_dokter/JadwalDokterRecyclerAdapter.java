package com.codelab.helmi.simrs.jadwal_dokter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codelab.helmi.simrs.R;
import com.codelab.helmi.simrs.jadwal_dokter.detail.DetailJadwalDokterFragment;

import java.util.ArrayList;
import java.util.List;

public class JadwalDokterRecyclerAdapter extends RecyclerView.Adapter<JadwalDokterRecyclerAdapter.MyHolder> implements Filterable {

    List<JadwalDokterModel> mList;
    List<JadwalDokterModel> mFilterList;
    Context ctx;
    FragmentManager fragmentManager;


    public JadwalDokterRecyclerAdapter(Context ctx, List<JadwalDokterModel> mList, FragmentManager fragmentManager) {
        this.mList = mList;
        this.ctx = ctx;
        this.fragmentManager = fragmentManager;
        this.mFilterList = mList;
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
        holder.nama.setText(mFilterList.get(position).getDokter());
        holder.spesialis.setText(mFilterList.get(position).getSpesialis());
        Glide.with(this.ctx).load(mFilterList.get(position).getGambar()).into(holder.ivDokter);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailJadwalDokterFragment detailJadwalDokterFragment = new DetailJadwalDokterFragment();
                JadwalDokterModel jadwalDokterModel = new JadwalDokterModel();

                jadwalDokterModel.setDokter(mFilterList.get(position).getDokter());
                jadwalDokterModel.setSpesialis(mFilterList.get(position).getSpesialis());
                jadwalDokterModel.setSenin(mFilterList.get(position).getSenin());
                jadwalDokterModel.setSelasa(mFilterList.get(position).getSelasa());
                jadwalDokterModel.setRabu(mFilterList.get(position).getRabu());
                jadwalDokterModel.setKamis(mFilterList.get(position).getKamis());
                jadwalDokterModel.setJumat(mFilterList.get(position).getJumat());
                jadwalDokterModel.setSabtu(mFilterList.get(position).getSabtu());
                jadwalDokterModel.setMinggu(mFilterList.get(position).getMinggu());
                jadwalDokterModel.setGambar(mFilterList.get(position).getGambar());

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
        return mFilterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    mFilterList = mList;
                } else {
                    List<JadwalDokterModel> filteredList = new ArrayList<>();
                    for (JadwalDokterModel jadwalDokterModel : mList) {
                        if (jadwalDokterModel.getDokter().toLowerCase().contains(charString.toLowerCase()) || jadwalDokterModel.getSpesialis().toLowerCase().contains(charString.toLowerCase()))
                        {
                            filteredList.add(jadwalDokterModel);
                        }
                    }
                    mFilterList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilterList = (List<JadwalDokterModel>) results.values;
                notifyDataSetChanged();
            }
        };
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
