<?php
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        require_once 'connect.php';

        $email = $_POST['email'];
        $password = $_POST['password'];

        $query_check = "SELECT * FROM admins where email = '$email'";
        $check = mysqli_fetch_array(mysqli_query($conn, $query_check)); 
        $json_array = array();
        $response = "";
        
        if (isset($check)) {
            $query_check_pass = "SELECT * FROM admins where email = '$email' and password = '$password'";
            $query_pass_result = mysqli_query($conn, $query_check_pass);
            $check_password = mysqli_fetch_array($query_pass_result);
            if (isset($check_password)) {
                $query_pass_result = mysqli_query($conn, $query_check_pass);
                while ($row = mysqli_fetch_assoc($query_pass_result)) {
                    $json_array[] = $row;
                }                
                $response = array(
                    'code' => true,
                    'status' => 'Sukses',
                    'user_list' => $json_array
                );
            } else {
                $response = array(
                    'code' => false,
                    'status' => 'Password salah, periksa kembali!',
                    'user_list' => $json_array
                );    
            }
        } else {
            $response = array(
                'code' => false,
                'status' => 'Data tidak ditemukan, lanjutkan registrasi?',
                'user_list' => $json_array
            );
        }
        print(json_encode($response));
        mysqli_close($conn);
    }
?>