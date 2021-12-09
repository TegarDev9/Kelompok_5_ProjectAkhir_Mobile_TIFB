package com.risquna.risqunaridho.pemesanan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.risquna.risqunaridho.R;

import java.util.List;

public class AdapterPemesanan extends RecyclerView.Adapter<AdapterPemesanan.HolderData> {
    private Context ctx;
    private List<DataPemesanan> listData;
    private int idcart;


    public AdapterPemesanan(Context ctx, List<DataPemesanan> listData) {
        this.ctx = ctx;
        this.listData = listData;

    }



    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.card_pemesanan, parent, false );
        HolderData holder = new HolderData ( layout );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {

        DataPemesanan dm = listData.get ( position );

        holder.tv_idcard.setText ( String.valueOf ( dm.getIdcart ()));
        holder.tv_code.setText  ( dm.getKodeorder () );
        holder.tv_status.setText ( dm.getStatus () );


    }

    @Override
    public int getItemCount() {
        return listData.size ();
    }

    public class HolderData extends RecyclerView.ViewHolder {

        TextView tv_idcard,tv_code,tv_status;

        public HolderData(View itemView) {
            super ( itemView );


            tv_idcard = itemView.findViewById ( R.id.tv_idcard );
            tv_code = itemView.findViewById ( R.id.tv_code );
            tv_status = itemView.findViewById ( R.id.tv_status );



        }


    }

}