package com.risquna.risqunaridho.produk;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.risquna.risqunaridho.R;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.HolderData>{

    private Context ctx;
    private List<DataProduk> listProduk;
    private int userid;

    public AdapterProduk(Context ctx, List<DataProduk> listProduk) {
        this.ctx = ctx;
        this.listProduk = listProduk;

    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.card_produk, parent, false );
        HolderData holder = new HolderData( layout );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {

        DataProduk dm = listProduk.get ( position );

        holder.tvidproduk.setText ( String.valueOf ( dm.getIdproduk () ));
        holder.tvidkategori.setText ( String.valueOf ( dm.getIdkategori () ));
        holder.tvnamaproduk.setText ( dm.getNamaproduk () );
        holder.tvdeskripsi.setText ( dm.getDeskripsi () );
        holder.tvrating.setText ( String.valueOf ( dm.getRating () ));
        holder.tvhargabefore.setText ( String.valueOf ( dm.getHargabefore() ));
        holder.tvhargaafter.setText ( String.valueOf ( dm.getHargaafter () ));
        holder.tvtgl.setText ( dm.getTgl () );
        holder.tvstok.setText ( String.valueOf ( dm.getStok() ));

        RequestOptions requestOptions = new RequestOptions();


        Glide.with(ctx)
                .load(listProduk.get(position).getGambar())
                .apply(requestOptions)
                .into(holder.imgproduk);
    }

    @Override
    public int getItemCount() {
        return listProduk.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvidproduk , tvidkategori, tvnamaproduk, tvdeskripsi, tvrating, tvhargabefore, tvhargaafter, tvtgl, tvstok;
        CircleImageView imgproduk;

        public HolderData(View itemView) {
            super ( itemView );

            tvidproduk = itemView.findViewById ( R.id.tv_idProduk );
            tvidkategori = itemView.findViewById ( R.id.tv_idKategori );
            tvnamaproduk = itemView.findViewById ( R.id.tv_namaProduk );
            imgproduk = itemView.findViewById ( R.id.img_produk );
            tvdeskripsi = itemView.findViewById ( R.id.tv_deskripsi );
            tvrating = itemView.findViewById ( R.id.tv_rating );
            tvhargabefore = itemView.findViewById ( R.id.tv_hargaBefore );
            tvhargaafter = itemView.findViewById ( R.id.tv_hargaAfter );
            tvtgl = itemView.findViewById ( R.id.tv_tgl );
            tvstok = itemView.findViewById ( R.id.tv_stok );

        }
    }
}
