<?php 
  require "init.php";
  $UID = $_POST["UID"];
  $n_pwd = $_POST["n_pwd"];
  
  $sql = "UPDATE `User` SET `PWD` = '$n_pwd' WHERE `UID` = '$UID'";
  mysqli_query($con,$sql);
?>
