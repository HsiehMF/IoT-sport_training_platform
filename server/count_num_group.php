<?php
  require "init.php";
  
  $sql_query = "SELECT  `group_num` FROM  `Group` ORDER BY  `group_num` DESC LIMIT 1";
  $result = mysqli_query($con,$sql_query);
  $row = mysqli_fetch_array($result);
  $group_num = $row["group_num"];
  $group_num = $group_num + 1;
  echo $group_num;
?>
