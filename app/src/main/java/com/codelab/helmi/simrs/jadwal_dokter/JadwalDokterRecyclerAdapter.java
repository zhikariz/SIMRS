package com.codelab.helmi.simrs.jadwal_dokter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codelab.helmi.simrs.R;

import java.util.List;

public class JadwalDokterRecyclerAdapter extends RecyclerView.Adapter<JadwalDokterRecyclerAdapter.MyHolder> {

    List<JadwalDokterModel> mList;
    Context ctx;

    public JadwalDokterRecyclerAdapter(Context ctx, List<JadwalDokterModel> mList) {
        this.mList = mList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_jadwal_dokter, parent, false);
        MyHolder holder = new MyHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.nama.setText(mList.get(position).getDokter());
        holder.usia.setText(mList.get(position).getSpesialis());
        holder.domisili.setText(mList.get(position).getSenin());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView nama, domisili, usia;

        public MyHolder(View v) {
            super(v);

            nama = (TextView) v.findViewById(R.id.tvNama);
            usia = (TextView) v.findViewById(R.id.tvUsia);
            domisili = (TextView) v.findViewById(R.id.tvDomisili);


        }

    }
}
