<?php

require("connect.php");
$response = array();


if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  // code...

 $userid = $_POST["userid"];


$perintah = "DELETE FROM users WHERE userid = '$userid'";
$eksekusi = mysqli_query($konek,$perintah);
$cek = mysqli_affected_rows($konek);

if($cek > 0){

  $response["kode"] = 1;
  $response["pesan"] = " data Berhasil di hapus";


}
 else{
   $response["kode"] = 0;
   $response["pesan"] = "gagal menghapus data";


 }


}
else{
  $response["kode"] = 0;
  $response["pesan"] = "Tidak ada Post Data";

}

echo json_encode($response);
mysqli_close($conn);
