<?php

require_once 'connect.php'; 
$perintah = "SELECT * FROM admins"; //memanggil table
$eksekusi = mysqli_query($conn, $perintah); 
$cek = mysqli_affected_rows($conn); //mengecek query berfungsi atau tidak

if($cek > 0){
    //membuat array
    $response["code"] = true;
    $response["status"] = "Data tersedia";
    $response["user_list"] = array();

    //menjemput data
    while($ambil = mysqli_fetch_object($eksekusi)){
        //membuat array baru untuk menampung data dari tabel user_detai kemudian di push ke array response dengan key ID 
        $F["idpetugas"] = $ambil->idpetugas;
        $F["nama"] = $ambil->nama;
        $F["email"] = $ambil->email;
        $F["password"] = $ambil->password;
        $F["notelp"] = $ambil->notelp;
        $F["role"] = $ambil->role;

        //push f ke response
        array_push($response["user_list"], $F);
    }
}else{
    //membuat array
    $response["code"] = false;
    $response["status"] = "Data tidak tersedia";
}

echo json_encode($response);
mysqli_close($conn);
?>