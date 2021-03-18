<?php
  require "init.php";
  $UID = $_POST["UID"];
  
  $sql = "SELECT `NAME`,`ID`,`BIRTHDAY`,`UNIT`,`GENDER`,`INFO` FROM `User` WHERE `UID`='$UID'";
  $result = mysqli_query($con,$sql);
  while ($row = mysqli_fetch_array($result)) {
    echo $row["NAME"].",".$row["ID"].",".$row["BIRTHDAY"].",".$row["UNIT"].",".$row["GENDER"].",".$row["INFO"];
  }
?>
