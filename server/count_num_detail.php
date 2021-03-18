<?php 
  require "init.php";
  
  $sql_query = "SELECT detail_id  FROM  `Detail` ORDER BY  `Detail`.`detail_id` DESC LIMIT 1";
  $result = mysqli_query($con,$sql_query);
  $row = mysqli_fetch_array($result);
  $detail_id = $row["detail_id"] + 1;

?>
