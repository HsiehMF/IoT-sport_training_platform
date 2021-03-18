<?php  
  require "init.php"; 
  require "count_num_detail.php";
  
  $student = $_POST["student"];
  $group_num = $_POST["group_num"];
  echo $student;
  require "find_uid_from_user.php";  // 找到 User 的 UID
  
  $sql_query = "INSERT INTO  `webappdb`.`Group` (`group_num`) VALUES ('$group_num')";
  $sql_query1 = "INSERT INTO  `webappdb`.`Detail` (`detail_id` ,`UID` ,`group_num`)VALUES ('$detail_id',  '$UID',  '$group_num');";

  $result = mysqli_query($con,$sql_query);
  $result1 = mysqli_query($con,$sql_query1);
  
?>
