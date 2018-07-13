package com.codelab.helmi.simrs.history;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.codelab.helmi.simrs.R;
import com.codelab.helmi.simrs.history.detail.DetailHistoryFragment;

import java.util.ArrayList;
import java.util.List;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.MyHolder> implements Filterable{

    List<HistoryData> mList;
    List<HistoryData> mFilterList;
    Context ctx;
    FragmentManager fragmentManager;


    public HistoryRecyclerAdapter(List<HistoryData> mList, Context ctx, FragmentManager fragmentManager) {
        this.mList = mList;
        this.mFilterList = mList;
        this.ctx = ctx;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_history, parent, false);
        MyHolder holder = new MyHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
            holder.hari_tanggal.setText(mFilterList.get(position).getTanggal_pesan());
            holder.dokter_poli.setText(mFilterList.get(position).getNama_dokter() + "/" + mFilterList.get(position).getNama_poli());
            holder.status.setText(mFilterList.get(position).getStatus_persetujuan());
            if(mFilterList.get(position).getStatus_persetujuan().equals("Belum disetujui")){
                holder.status.setTextColor(Color.parseColor("#CCCC00"));
            }
            else if(mFilterList.get(position).getStatus_persetujuan().equals("Disetujui")){
                holder.status.setTextColor(Color.parseColor("#008000"));
            } else {
                holder.status.setTextColor(Color.parseColor("#ff0000"));
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailHistoryFragment detailHistoryFragment = new DetailHistoryFragment();
                    HistoryData historyData = new HistoryData();

                    historyData.setNo_rm(mFilterList.get(position).getNo_rm());
                    historyData.setTanggal_pesan(mFilterList.get(position).getTanggal_pesan());
                    historyData.setNama_poli(mFilterList.get(position).getNama_poli());
                    historyData.setNama_dokter(mFilterList.get(position).getNama_dokter());
                    historyData.setKategori(mFilterList.get(position).getKategori());
                    historyData.setNama_asuransi(mFilterList.get(position).getNama_asuransi());
                    historyData.setStatus_persetujuan(mFilterList.get(position).getStatus_persetujuan());
                    historyData.setKeterangan(mFilterList.get(position).getKeterangan());

                    Bundle bundle = new Bundle();
                    bundle.putParcelable(detailHistoryFragment.EXTRA_HISTORY, historyData);
                    detailHistoryFragment.setArguments(bundle);
                    fragmentManager.beginTransaction()
                            .replace(R.id.frame_container, detailHistoryFragment, detailHistoryFragment.getClass().getSimpleName())
                            .addToBackStack(detailHistoryFragment.getClass().getSimpleName())
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

                if(charString.isEmpty()){
                    mFilterList = mList;
                } else {
                    List<HistoryData> filteredList = new ArrayList<>();
                    for(HistoryData historyData: mList){
                        if(historyData.getNama_dokter().toLowerCase().contains(charString.toLowerCase()) || historyData.getTanggal_pesan().toLowerCase().contains(charString.toLowerCase()) || historyData.getNama_poli().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(historyData);
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
                mFilterList = (List<HistoryData>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView hari_tanggal, dokter_poli, status;

        public MyHolder(View v) {
            super(v);

            hari_tanggal = (TextView) v.findViewById(R.id.tv_history_tanggal);
            dokter_poli = (TextView) v.findViewById(R.id.tv_history_dokter_poli);
            status = (TextView) v.findViewById(R.id.tv_history_status);


        }

    }
}
