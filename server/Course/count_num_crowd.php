<?php
  require "../init.php";

  $sql_query = "SELECT crowd_num FROM `Crowd` ORDER BY  `Crowd`.`crowd_num` DESC LIMIT 1";
  $result = mysqli_query($con,$sql_query);
  $row = mysqli_fetch_array($result);
  $crowd_num = $row["crowd_num"] + 1;

?>
