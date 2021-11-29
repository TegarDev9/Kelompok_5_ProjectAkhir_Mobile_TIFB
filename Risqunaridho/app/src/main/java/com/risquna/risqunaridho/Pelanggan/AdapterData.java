package com.risquna.risqunaridho.Pelanggan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.risquna.risqunaridho.R;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private Context ctx;
    private List<DataModel> listData;
    private int userid;


    public AdapterData(Context ctx, List<DataModel> listData) {
        this.ctx = ctx;
        this.listData = listData;

    }


    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.card_item, parent, false );
        HolderData holder = new HolderData ( layout );
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        DataModel dm = listData.get ( position );

        holder.tvuserId.setText ( String.valueOf ( dm.getUserid () ) );
        holder.tvNama.setText ( dm.getNama () );
        holder.tvemail.setText ( dm.getEmail () );
        holder.tvpassword.setText ( dm.getPassword () );
        holder.tvverifcode.setText ( dm.getVerif_code () );
        holder.tvisverified.setText ( dm.getIs_verified () );

    }

    @Override
    public int getItemCount() {
        return listData.size ();
    }

    public class HolderData extends RecyclerView.ViewHolder {

        TextView tvuserId, tvNama, tvemail, tvpassword, tvverifcode, tvisverified;

        public HolderData(View itemView) {
            super ( itemView );

            tvuserId = itemView.findViewById ( R.id.tv_userid );
            tvNama = itemView.findViewById ( R.id.tv_nama );
            tvemail = itemView.findViewById ( R.id.tv_email );
            tvpassword = itemView.findViewById ( R.id.tv_password );
            tvverifcode = itemView.findViewById ( R.id.tv_verif_code );
            tvisverified = itemView.findViewById ( R.id.tv_is_verified );


        }


    }

}