<?php
require("connect.php");
$perintah = "SELECT * FROM users";
$eksekusi = mysqli_query($conn, $perintah);
$cek = mysqli_affected_rows($conn);

if($cek > 0){
   $response["kode"] = 1;
   $response["pesan"] = "Data tersedia";
   $response['data']  = array();


   while($ambil = mysqli_fetch_object($eksekusi)){
       $F["userid"] = $ambil -> userid;
        $F["nama"] = $ambil -> nama;
         $F["notelp"] = $ambil -> notelp;
          $F["alamat"] = $ambil -> alamat;
           $F["email"] = $ambil -> email;
            $F["password"] = $ambil -> password;


           array_push($response["data"], $F);
   }

}

else{
  $response["kode"] = 0;
  $response["pesan"] = "Data tidak tersedia";

}

echo json_encode($response);
mysqli_close($conn);
