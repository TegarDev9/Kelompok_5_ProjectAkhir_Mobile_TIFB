<?php

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';

$key = $_POST['key'];
$id      = $_POST['idproduk'];
$gambar = $_POST['gambar'];

if ( $key == "delete" ){

    $query = "DELETE FROM produk WHERE idproduk='$id' ";

        if ( mysqli_query($conn, $query) ){

            $iparr = split ("/", $gambar);
            $gambar_split = $iparr[5];

            if ( unlink("produk_picture/".$gambar_split) ){

                $result["value"] = "1";
                $result["message"] = "Success!";

                echo json_encode($result);
                mysqli_close($conn);

            } else {

                $response["value"] = "0";
                $response["message"] = "Error to delete a image! ".mysqli_error($conn);
                echo json_encode($response);

                mysqli_close($conn);
            }

        }
        else {

            $response["value"] = "0";
            $response["message"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
        }

} else {
    $response["value"] = "0";
    $response["message"] = "Error! ".mysqli_error($conn);
    echo json_encode($response);

    mysqli_close($conn);
}

?>
