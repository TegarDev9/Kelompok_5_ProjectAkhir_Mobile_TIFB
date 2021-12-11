<?php
require_once 'connect.php';
$perintah = "SELECT * FROM produk"; //memanggil table
$eksekusi = mysqli_query($conn, $perintah);
$cek = mysqli_affected_rows($conn); //mengecek query berfungsi atau tidak

if($cek > 0){
    //membuat array
    $response["code"] = 1;
    $response["status"] = "Data tersedia";
    $response["produk_list"] = array();

    //menjemput data
    while($ambil = mysqli_fetch_object($eksekusi)){
        //membuat array baru untuk menampung data dari tabel user_detai kemudian di push ke array response dengan key ID
        $F["idproduk"] = $ambil->idproduk;
        $F["idkategori"] = $ambil->idkategori;
        $F["namaproduk"] = $ambil->namaproduk;
        $F["gambar"] = $ambil->gambar;
        $F["deskripsi"] = $ambil->deskripsi;
        $F["rating"] = $ambil->rating;
        $F["hargabefore"] = $ambil->hargabefore;
        $F["hargaafter"] = $ambil->hargaafter;
        $F["tgl"] = $ambil->tgl;
        $F["stok"] = $ambil->stok;


        //push f ke response
        array_push($response["produk_list"], $F);
    }
}else{
    //membuat array
    $response["code"] = false;
    $response["status"] = "Data tidak tersedia";
}

echo json_encode($response);
mysqli_close($conn);
?>
