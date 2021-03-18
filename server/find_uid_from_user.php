<?php
  require "init.php";
  
  $sql_query = "SELECT UID FROM `User` WHERE User.NAME = '$student'";
  $result = mysqli_query($con,$sql_query);
  $row = mysqli_fetch_array($result);
  $UID = $row["UID"];
  echo $UID;
?>
