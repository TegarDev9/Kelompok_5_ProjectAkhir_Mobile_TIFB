package com.risquna.risqunaridho.produk;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.R;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.HolderData>{

    private Context ctx;
    private List<DataProduk> listProduk;
    private List<DataProduk> listUpdate;
    private int idproduk;

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

        DataProduk dm = listProduk.get(position);

        holder.tvidproduk.setText(String.valueOf(dm.getIdproduk()));
        holder.tvidkategori.setText(String.valueOf(dm.getIdkategori()));
        holder.tvnamaproduk.setText(dm.getNamaproduk());
        holder.tvdeskripsi.setText(dm.getDeskripsi());
        holder.tvrating.setText(String.valueOf( dm.getRating ()));
        holder.tvhargabefore.setText(String.valueOf(dm.getHargabefore()));
        holder.tvhargaafter.setText(String.valueOf(dm.getHargaafter ()));
        holder.tvtgl.setText(dm.getTgl());
        holder.tvstok.setText(String.valueOf(dm.getStok()));

        String urlGambar = ApiClient.BASE_URL + "risqunastore/" + dm.getGambar();
        Glide.with(ctx)
                .load(urlGambar)// load gambar
                .placeholder(R.drawable.logo)// sebelum load gambar dari data
                .error(R.drawable.ic_warning) // load error
                .into(holder.imgproduk);
        //Toast.makeText(ctx, "gambar :    " + ApiClient.BASE_URL +urlGambar, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return listProduk.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvidproduk , tvidkategori, tvnamaproduk, tvdeskripsi, tvrating, tvhargabefore, tvhargaafter, tvtgl, tvstok;
        ImageView imgproduk;

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

            // Membuat dialog
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih operasi yang akan dilakukan");
                    dialogPesan.setCancelable(true);

                    idproduk = Integer.parseInt(tvidproduk.getText().toString());

                    dialogPesan.setPositiveButton("Delete", (dialog, which) -> {
                        deleteProduk();
                        dialog.dismiss();
                        ((produkActivity)ctx).retrieveProduk();
                    });
                    dialogPesan.setNegativeButton("Edit", (dialog, which) -> {
                        getProduk();
                    });
                    dialogPesan.show();

                    return false;
                }
            });

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    getProduk2();
//                }
//            });
        }

        private void deleteProduk() {
            ApiInterface ardData = ApiClient.koneksi().create(ApiInterface.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteProduk(idproduk);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int code = response.body().getCode();
                    String status = response.body().getStatus();

                    Toast.makeText(ctx, "Code : "+code+ " | Status : "+status , Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal menghubungi server"+ t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void getProduk(){
            ApiInterface ardData = ApiClient.koneksi().create(ApiInterface.class);
            Call<ResponseModel> getData = ardData.ardGetProduk(idproduk);

            getData.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int code = response.body().getCode();
                    String status = response.body().getStatus();
                    listUpdate = response.body().getProduk_list();

                    //Menangkap data
                    int varIdproduk = listUpdate.get(0).getIdproduk();
                    int varIdkategori = listUpdate.get(0).getIdkategori();
                    String varNamaproduk = listUpdate.get(0).getNamaproduk();
                    String varImgproduk = listUpdate.get(0).getGambar();
                    String varDeskripsi = listUpdate.get(0).getDeskripsi();
                    int varRating = listUpdate.get(0).getRating();
                    int varHargabefore = listUpdate.get(0).getHargabefore();
                    int varHargaafter = listUpdate.get(0).getHargaafter();
                    String varTgl = listUpdate.get(0).getTgl();
                    int varStok = listUpdate.get(0).getStok();


                    //Mengirim data
                    Intent kirim = new Intent(itemView.getContext(), UpdateProdukActivity.class);
                    kirim.putExtra("xIdproduk", varIdproduk);
                    kirim.putExtra("xIdkategori", varIdkategori);
                    kirim.putExtra("xNamaproduk", varNamaproduk);
                    kirim.putExtra("xImgproduk", varImgproduk);
                    kirim.putExtra("xDeskripsi", varDeskripsi);
                    kirim.putExtra("xRating", varRating);
                    kirim.putExtra("xHargabefore", varHargabefore);
                    kirim.putExtra("xHargaafter", varHargaafter);
                    kirim.putExtra("xTgl", varTgl);
                    kirim.putExtra("xStok", varStok);
                    itemView.getContext().startActivity(kirim);
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal menghubungi server" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
