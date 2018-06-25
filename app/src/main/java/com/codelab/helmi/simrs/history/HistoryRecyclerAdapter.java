package com.codelab.helmi.simrs.history;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codelab.helmi.simrs.R;

import java.util.List;

public class HistoryRecyclerAdapter  extends RecyclerView.Adapter<HistoryRecyclerAdapter.MyHolder>{
    List<HistoryData> mList;
    Context ctx;
    FragmentManager fragmentManager;

    public HistoryRecyclerAdapter(List<HistoryData> mList, Context ctx, FragmentManager fragmentManager) {
        this.mList = mList;
        this.ctx = ctx;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_history,parent,false);
        MyHolder holder = new MyHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.hari_tanggal.setText(mList.get(position).getTanggal_pesan());
        holder.dokter_poli.setText(mList.get(position).getNama_dokter()+"/"+mList.get(position).getNama_poli());
        holder.status.setText(mList.get(position).getStatus_persetujuan());
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
        TextView hari_tanggal,dokter_poli,status;

        public MyHolder(View v) {
            super(v);

            hari_tanggal = (TextView) v.findViewById(R.id.tv_history_tanggal);
            dokter_poli = (TextView) v.findViewById(R.id.tv_history_dokter_poli);
            status = (TextView) v.findViewById(R.id.tv_history_status);


        }

    }
}
