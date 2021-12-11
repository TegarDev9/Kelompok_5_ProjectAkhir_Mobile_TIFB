<?php

require_once 'connect.php';

$key = $_POST['key'];

$idproduk  = $_POST['idproduk'];
$idkategori= $_POST['idkategori'];
$namaproduk= $_POST['namaproduk'];
$deskripsi = $_POST['deskripsi'];
$rating    = $_POST['rating'];
$hargabefore = $_POST['hargabefore'];
$hargaafter  = $_POST['hargaafter'];
$stok    = $_POST['stok'];
$gambar  = $_POST['gambar'];

if ( $key == "insert" ){

    $tgl_newformat = date('Y-m-d', strtotime($tgl));

    $query = "INSERT INTO produk (idproduk,idkategori,namaproduk,deskripsi,rating,hargabefore,hargaafter,stok,tgl,gambar)
    VALUES ('$idproduk','$idkategori','$namaproduk', '$deskripsi', '$rating','$hargabefore','$hargaafter' '$stok', '$tgl_newformat','$gambar') ";

        if ( mysqli_query($conn, $query) ){

            if ($gambar == null) {

                $finalPath = "RisqunaRidho_Mobile/produk/produk_rr.png";
                $result["value"] = "1";
                $result["message"] = "Success";

                echo json_encode($result);
                mysqli_close($conn);

            } else {

                $idproduk = mysqli_insert_id($conn);
                $path = "RisqunaRidho_Mobile/produk_gambar/$idproduk.jpeg";
                $finalPath = "/produk/".$path;

                $insert_gambar = "UPDATE produk SET gambar='$finalPath' WHERE idproduk='$idproduk' ";

                if (mysqli_query($conn, $insert_gambar)) {

                    if ( file_put_contents( $path, base64_decode($gambar) ) ) {

                        $result["value"] = "1";
                        $result["message"] = "Success!";

                        echo json_encode($result);
                        mysqli_close($conn);

                    } else {

                        $response["value"] = "0";
                        $response["message"] = "Error! ".mysqli_error($conn);
                        echo json_encode($response);

                        mysqli_close($conn);
                    }

                }
            }

        }
        else {
            $response["value"] = "0";
            $response["message"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
        }
}

?>
