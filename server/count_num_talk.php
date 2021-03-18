<?php
  require "init.php";

  $sql_query = "SELECT talk_num  FROM `Talk` ORDER BY  `Talk`.`talk_num` DESC LIMIT 1";
  $result = mysqli_query($con,$sql_query);
  $row = mysqli_fetch_array($result);
  $talk_num = $row["talk_num"] + 1;

?>
