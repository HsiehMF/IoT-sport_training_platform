<?php
  require "init.php";
  $UID = $_POST["UID"];  

  $sql = "SELECT `PWD` FROM `User` WHERE `UID`= '$UID'";  
  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $o_pwd = $row["PWD"];
  echo $o_pwd;

?>
