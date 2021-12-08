package com.risquna.risqunaridho.petugas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.risquna.risqunaridho.R;

import java.util.List;

public class AdapterPetugas extends RecyclerView.Adapter<AdapterPetugas.HolderData> {
    private Context ctx;
    private List<DataPetugas> listData;
    private int userid;


    public AdapterPetugas(Context ctx, List<DataPetugas> listData) {
        this.ctx = ctx;
        this.listData = listData;

    }



    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.card_petugas, parent, false );
        HolderData holder = new HolderData ( layout );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {

        DataPetugas dm = listData.get ( position );

        holder.tvidpetugas.setText ( String.valueOf ( dm.getIdpetugas ()
        ) );
        holder.tvnama.setText ( dm.getNama () );
        holder.tvnotelp.setText ( dm.getNotelp () );
        holder.tvemail.setText ( dm.getEmail () );
        holder.tvpassword.setText ( dm.getPassword () );
        holder.tvrole.setText ( dm.getRole () );

    }

    @Override
    public int getItemCount() {
        return listData.size ();
    }

    public class HolderData extends RecyclerView.ViewHolder {

        TextView tvidpetugas , tvnama, tvnotelp, tvemail, tvpassword, tvrole;

        public HolderData(View itemView) {
            super ( itemView );

            tvidpetugas = itemView.findViewById ( R.id.tv_idpetugas );
            tvnama = itemView.findViewById ( R.id.tv_nama );
            tvnotelp = itemView.findViewById ( R.id.tv_notelp );
            tvemail = itemView.findViewById ( R.id.tv_email );
            tvpassword = itemView.findViewById ( R.id.tv_password );
            tvrole = itemView.findViewById ( R.id.tv_role );


        }


    }

}