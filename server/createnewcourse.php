<?php  
  require "init.php";  
  $i_name = $_POST["i_name"];  
  $i_sub_name = $_POST["i_sub_name"];  
  $i_times = $_POST["i_times"];
  $sql = "INSERT INTO  `webappdb`.`COURSE` (`c_num` ,`c_item_name` ,`c_item_sub_name` ,`c_item_times`)VALUES ( '',  '$i_name',  '$i_sub_name',  '$i_times')";
 
  $result = mysqli_query($con,$sql);  
?>

