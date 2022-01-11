package com.risquna.risqunaridho.petugas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterPetugas extends RecyclerView.Adapter<AdapterPetugas.HolderData> {
    private Context ctx;
    private List<DataPetugas> listData;
    private List<DataPetugas> listUpdate;
    private int idpetugas;

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
        holder.tvnama.setText ( dm.getNama() );
        holder.tvnotelp.setText ( dm.getNotelp () );
        holder.tvemail.setText ( dm.getEmail () );
        holder.tvpassword.setText ( dm.getPassword () );
        holder.tvrole.setText ( String.valueOf (  dm.getRole () ));

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



            // Membuat dialog
            itemView.setOnLongClickListener ( new View.OnLongClickListener () {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder ( ctx );
                    dialogPesan.setMessage ( "Pilih operasi yang akan dilakukan" );
                    dialogPesan.setCancelable ( true );

                    idpetugas = Integer.parseInt ( tvidpetugas.getText ().toString () );

                    dialogPesan.setPositiveButton ( "Delete", (dialog, which) -> {
                        deletePetugas ();
                        dialog.dismiss ();
                        ((petugasActivity) ctx).retrieveData ();
                    } );
                    dialogPesan.setNegativeButton ( "Edit", (dialog, which) -> {
                        getPetugas ();
                        System.out.println("idpetugas" + idpetugas);
                    } );
                    dialogPesan.show ();

                    return false;
                }
            } );
        }

        private void deletePetugas() {
            ApiInterface ardData = ApiClient.koneksi ().create ( ApiInterface.class );
            Call<ResponseModel> hapusData = ardData.ardDeletePetugas ( idpetugas );

            hapusData.enqueue ( new Callback<ResponseModel> () {
                @Override
                public void onResponse(Call<com.risquna.risqunaridho.petugas.ResponseModel> call, Response<ResponseModel> response) {
                    int code = response.body ().getKode ();
                    String status = response.body ().getPesan ();

                    Toast.makeText ( ctx, " Status : " + status, Toast.LENGTH_SHORT ).show ();
                }

                @Override
                public void onFailure(Call<com.risquna.risqunaridho.petugas.ResponseModel> call, Throwable t) {
                    Toast.makeText ( ctx, "Gagal menghubungi server" + t.getMessage (), Toast.LENGTH_SHORT ).show ();
                }
            } );
        }

        private void getPetugas() {
            ApiInterface ardData = ApiClient.koneksi ().create ( ApiInterface.class );
            Call<com.risquna.risqunaridho.petugas.ResponseModel> getData = ardData.ardGetPetugas ( idpetugas );

            getData.enqueue ( new Callback<> () {
                @Override
                public void onResponse(Call<com.risquna.risqunaridho.petugas.ResponseModel> call, Response<com.risquna.risqunaridho.petugas.ResponseModel> response) {
                    int code = response.body ().getKode ();
                    String status = response.body ().getPesan ();
                    listUpdate = response.body ().getData ();

                    int varIdpetugas = listUpdate.get ( 0 ).getIdpetugas ();
                    String varNama = listUpdate.get ( 0 ).getNama();
                    String varNotelp = listUpdate.get ( 0 ).getNotelp ();
                    String varEmail = listUpdate.get ( 0 ).getEmail ();
                    String varPassword = listUpdate.get ( 0 ).getPassword ();
                    int varRole = listUpdate.get ( 0 ).getRole ();


                    //Mengirim data
                    Intent kirim = new Intent ( itemView.getContext (), updatepetugasActivity.class );
                    kirim.putExtra ( "xIdpetugas", varIdpetugas );
                    kirim.putExtra ( "xNamapetugas", varNama );
                    kirim.putExtra ( "xNotelp", varNotelp );
                    kirim.putExtra ( "xEmail", varEmail );
                    kirim.putExtra ( "xPassword", varPassword );
                    kirim.putExtra ( "xRole", varRole );
                    itemView.getContext ().startActivity ( kirim );
                }

                @Override
                public void onFailure(Call<com.risquna.risqunaridho.petugas.ResponseModel> call, Throwable t) {
                    Toast.makeText ( ctx, "Gagal menghubungi server" + t.getMessage (), Toast.LENGTH_SHORT ).show ();
                }
            } );
        }


    }


}

