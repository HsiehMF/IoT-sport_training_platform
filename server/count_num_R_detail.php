<?php
  require "init.php";

  $sql_query = "SELECT R_detail_num FROM `R_Detail` ORDER BY `R_Detail`.`R_detail_num` DESC LIMIT 1";
  $result = mysqli_query($con,$sql_query);
  $row = mysqli_fetch_array($result);
  $R_detail_num = $row["R_detail_num"] + 1;

?>
