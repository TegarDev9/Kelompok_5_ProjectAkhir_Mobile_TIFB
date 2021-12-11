<?php

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';

$query = "SELECT * FROM produk ORDER BY idproduk DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response,
    array(
        'idproduk'        =>$row['idproduk'],
        'idkategori'      =>$row['idkategori'],
        'namaproduk'   =>$row['namaproduk'],
        'gambar'   =>"http://$server_name" . $row['gambar'],
        'deskripsi'    =>$row['deskripsi'],
        'hargabefore'    =>$row['hargabefore'],
        'hargaafter'    =>$row['hargaafter'],
        'tgl'     =>date('d M Y', strtotime($row['tgl'])),
        'stok'   => $row['stok'])

);
}

echo json_encode($response);

mysqli_close($conn);

?>
