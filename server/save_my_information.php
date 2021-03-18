<?php 
  require "init.php";
  $name = $_POST["name"];
  $birth = $_POST["birth"];
  $unit = $_POST["unit"];
  $gender = $_POST["gender"];
  $info = $_POST["info"];
  $UID = $_POST["UID"];
  
  $sql = "UPDATE `User` SET `NAME` =  '$name',`BIRTHDAY` = '$birth',`UNIT` =  '$unit',`GENDER` =  '$gender',`INFO` = '$info' WHERE `UID` ='$UID'";
  mysqli_query($con,$sql);

  require "my_information.php";  // 在一次讀取資料，印到EditText
  
?>
