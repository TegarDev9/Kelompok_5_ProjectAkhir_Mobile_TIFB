<?php

require("koneksi.php");
$response = array();


if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  // code...

  $userid = $_POST["userid"];
   $nama = $_POST["nama"];
    $notelp = $_POST["notelp"];
    $alamat = $_POST["alamat"];
     $email = $_POST["email"];
   $pssword = $_POST["password"];



$perintah = "INSERT INTO users (userid, nama, notelp, alamat, email, password) VALUES('$userid', '$nama', '$notelp', '$alamat', '$email', '$password')";
$eksekusi = mysqli_query($konek,$perintah);
$cek = mysqli_affected_rows($konek);

if($cek > 0){

  $response["kode"] = 1;
  $response["pesan"] = "Simpan data Berhasil";


}
 else{
   $response["kode"] = 0;
   $response["pesan"] = "gagal mengimpan data";


 }


}
else{
  $response["kode"] = 0;
  $response["pesan"] = "Tidak ada Post Data";

}

echo json_encode($response);
mysqli_close($konek);
