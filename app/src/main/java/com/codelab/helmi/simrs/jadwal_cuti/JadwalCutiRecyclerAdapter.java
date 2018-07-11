package com.codelab.helmi.simrs.jadwal_cuti;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codelab.helmi.simrs.R;

import java.util.ArrayList;
import java.util.List;

public class JadwalCutiRecyclerAdapter extends RecyclerView.Adapter<JadwalCutiRecyclerAdapter.MyHolder> implements Filterable{

    List<JadwalCutiModel> mList;
    List<JadwalCutiModel> mFilterList;
    Context ctx;

    public JadwalCutiRecyclerAdapter(Context ctx, List<JadwalCutiModel> mList){
        this.mList = mList;
        this.ctx = ctx;
        this.mFilterList = mList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_jadwal_cuti, parent, false);
        MyHolder holder = new MyHolder(layout);
        return holder;
    }

    public void onBindViewHolder(@NonNull MyHolder holder, int position){
        holder.nama.setText(mFilterList.get(position).getNama());
        holder.spesialisasi.setText(mFilterList.get(position).getSpesialisasi());
        holder.keterangan.setText(mFilterList.get(position).getKeterangan());
        holder.tanggal.setText(mFilterList.get(position).getTanggal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                    List<JadwalCutiModel> filteredList = new ArrayList<>();
                    for(JadwalCutiModel jadwalCutiModel : mList){
                        if(jadwalCutiModel.getNama().toLowerCase().contains(charString.toLowerCase()) || jadwalCutiModel.getSpesialisasi().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(jadwalCutiModel);
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
                mFilterList = (List<JadwalCutiModel>) results.values;
                notifyDataSetChanged();
            }
        };
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
