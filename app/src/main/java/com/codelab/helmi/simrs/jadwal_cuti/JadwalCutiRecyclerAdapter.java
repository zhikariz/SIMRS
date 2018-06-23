package com.codelab.helmi.simrs.jadwal_cuti;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codelab.helmi.simrs.R;

import java.util.List;

public class JadwalCutiRecyclerAdapter extends RecyclerView.Adapter<JadwalCutiRecyclerAdapter.MyHolder> {

    List<JadwalCutiModel> mList;
    Context ctx;

    public JadwalCutiRecyclerAdapter(Context ctx, List<JadwalCutiModel> mList){
        this.mList = mList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_jadwal_cuti, parent, false);
        MyHolder holder = new MyHolder(layout);
        return holder;
    }

    public void onBindViewHolder(@NonNull MyHolder holder, int position){
        holder.nama.setText(mList.get(position).getNama());
        holder.spesialisasi.setText(mList.get(position).getSpesialisasi());
        holder.keterangan.setText(mList.get(position).getKeterangan());
        holder.tanggal.setText(mList.get(position).getTanggal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView nama, spesialisasi, keterangan, tanggal;

        public MyHolder(View v) {
            super(v);

            nama = (TextView) v.findViewById(R.id.tvNama);
            spesialisasi = (TextView) v.findViewById(R.id.tvSpesialisasi);
            keterangan = (TextView) v.findViewById(R.id.tvKeterangan);
            tanggal = (TextView) v.findViewById(R.id.tvTanggal);
        }

    }
}
