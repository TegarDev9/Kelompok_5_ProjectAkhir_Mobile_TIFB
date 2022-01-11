package com.risquna.risqunaridho.transaksi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterTransaksi extends RecyclerView.Adapter<AdapterTransaksi.HolderData> {
    private Context ctx;
    private List<DataTransaksi> listData;
    private List<DataTransaksi> listUpdate;
    private int idcart;

    public AdapterTransaksi(Context ctx, List<DataTransaksi> listData) {
        this.ctx = ctx;
        this.listData = listData;

    }



    @Override
    public AdapterTransaksi.HolderData onCreateViewHolder(ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.card_update_transaksi, parent, false );
        AdapterTransaksi.HolderData holder = new AdapterTransaksi.HolderData( layout );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTransaksi.HolderData holder, int position) {

        DataTransaksi dm = listData.get ( position );

        holder.tvIdcart.setText ( String.valueOf ( dm.getIdcart ()) );
        holder.tvkode.setText( dm.getKodeorder () );
        holder.tvUserid.setText ( String.valueOf (dm.getUserid ()));
        holder.tvnama.setText ( dm.getNama() );
        holder.tvTotal.setText ( String.valueOf (dm.getTotalbelanja () ));
        holder.tvTanggal.setText ( dm.getTglbelanja() );
        holder.tvStatus.setText ( dm.getStatus () );

    }

    @Override
    public int getItemCount() {
        return listData.size ();
    }

    public class HolderData extends RecyclerView.ViewHolder {

        TextView tvIdcart, tvkode, tvUserid, tvnama, tvTotal, tvTanggal, tvStatus;

        public HolderData(View itemView) {
            super(itemView);

            tvIdcart = itemView.findViewById(R.id.tv_idcart);
            tvkode = itemView.findViewById(R.id.tv_kodeOrder);
            tvnama = itemView.findViewById ( R.id.tv_nama );
            tvUserid = itemView.findViewById(R.id.tv_userID);
            tvnama = itemView.findViewById(R.id.tv_namaUser);
            tvTotal = itemView.findViewById(R.id.tv_totalBelanja);
            tvTanggal = itemView.findViewById(R.id.tv_tglBelanja);
            tvStatus = itemView.findViewById(R.id.tv_status);


            // Membuat dialog
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    idcart = Integer.parseInt(tvIdcart.getText().toString());
                    getPesanan();
                }
            });
        }




        private void getPesanan() {
            ApiInterface ardData = ApiClient.koneksi ().create ( ApiInterface.class );
            Call<com.risquna.risqunaridho.transaksi.ResponseModel> getData = ardData.ardGetPesanan (idcart);

            getData.enqueue ( new Callback<>() {
                @Override
                public void onResponse(Call<com.risquna.risqunaridho.transaksi.ResponseModel> call, Response<com.risquna.risqunaridho.transaksi.ResponseModel> response) {
                    int code = response.body ().getCode ();
                    String status = response.body ().getStatus ();
                    listUpdate = response.body ().getData ();

                    int varIdcart = listUpdate.get ( 0 ).getIdcart ();
                    String varKodeOrder = listUpdate.get ( 0 ).getKodeorder ();
                    int varUserid = listUpdate.get ( 0 ).getUserid ();
                    String varNama = listUpdate.get ( 0 ).getNama();
                    int varTotalBelanja = listUpdate.get ( 0 ).getTotalbelanja ();
                    String varTglblnja = listUpdate.get ( 0 ).getTglbelanja();
                    String varstatus = listUpdate.get(0).getStatus();


                    //Mengirim data
                    Intent kirim = new Intent ( itemView.getContext (), UpdateTransaksiActivity.class );
                    kirim.putExtra ( "xIdcart", varIdcart );
                    kirim.putExtra ( "xKodeOrder", varKodeOrder );
                    kirim.putExtra ( "xUserid", varUserid );
                    kirim.putExtra ( "xNama", varNama );
                    kirim.putExtra ( "xTotalBelanja", varTotalBelanja );
                    kirim.putExtra ( "xTglBelanja", varTglblnja );
                    kirim.putExtra ( "xStatus", varstatus );
                    itemView.getContext ().startActivity ( kirim );
                }

                @Override
                public void onFailure(Call<com.risquna.risqunaridho.transaksi.ResponseModel> call, Throwable t) {
                    Toast.makeText ( ctx, "Gagal menghubungi server" + t.getMessage (), Toast.LENGTH_SHORT ).show ();
                }
            } );
        }
    }
}