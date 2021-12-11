<?php

if($_SERVER['REQUEST_METHOD'] == 'POST') {
    require_once 'connect.php';       
    
    $nama = $_POST["nama"];
    $notelp = $_POST["notelp"];
    $email = $_POST["email"];
    $password = $_POST["password"];
    $role = $_POST["role"];

    $query_check = "SELECT * FROM admins where email = '$email'";
    $check = mysqli_fetch_array(mysqli_query($conn, $query_check));        
    $json_array = array();
    $response = "";

    if (isset($check)) {
        $response = array(
            'code' => false,
            'status' => 'User has been registered!'
        );
    } else {
        $query_insert = "INSERT INTO admins (nama, notelp, email, password, role) VALUES('$nama', '$notelp', '$email', '$password', '$role')";
        if (mysqli_query($conn, $query_insert)) {
            $response = array(
                'code' => true,
                'status' => 'User Registered'
            );
        } else {
            $response = array(
                'code' => false,
                'status' => 'Registered Error!'
            );
        }
    }

    print(json_encode($response));
    mysqli_close($conn);

} elseif($_SERVER['REQUEST_METHOD'] == 'GET') {
    require_once 'connect.php';      
    $query_insert = "SELECT * FROM admins";
    $result = mysqli_query($conn, $query_insert);
    $json_array = array();
    $response = "";

    if (isset($result)) {
        while ($row = mysqli_fetch_assoc($result)) {
            $json_array[] = $row;
        }
        $response = array(
            'code' => true,
            'status' => 'Successful',
            'user_list' => $json_array
        );   
    } else {
        $response = array(
            'code' => false,
            'status' => 'Error',
            'user_list' => 0
        );
    }
    print(json_encode($response));
    mysqli_close($conn);
}
?>
