<?php

$db = mysqli_connect("localhost","root","","webdatabase");

if(!$db)
{
    die("Connection failed: " . mysqli_connect_error());
}

?>
