<?php

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';

$key = $_POST['key'];

if ( $key == "update" ){

    $idproduk        = $_POST['idproduk'];
    $namaproduk       = $_POST['namaproduk'];
    $deskripsi    = $_POST['deskripsi'];
    $rating      = $_POST['rating'];
    $stok     = $_POST['stok'];
    $tgl     = $_POST['tgl'];
    $gambar    = $_POST['gambar'];

    $tgl =  date('Y-m-d', strtotime($tgl));

    $query = "UPDATE produk SET
    namaproduk='$namaproduk',
    deskripsi='$deskripsi',
    rating='$rating',
    stok='$stok',
    tgl='$tgl'
    WHERE idproduk='$idproduk' ";

        if ( mysqli_query($conn, $query) ){

            if ($picture == null) {

                $result["value"] = "1";
                $result["message"] = "Success";

                echo json_encode($result);
                mysqli_close($conn);

            } else {

                $path = "produk_gambar/$id.jpeg";
                $finalPath = "/produk/".$path;

                $insert_gambar = "UPDATE produk SET picture='$finalPath' WHERE idproduk='$idproduk' ";

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
