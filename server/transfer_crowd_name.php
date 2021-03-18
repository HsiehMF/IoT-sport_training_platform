<?php

  $sql = "SELECT `crowd_num` FROM `Crowd` WHERE `crowd_name`='$crowd_name' and `UID`='$UID'";
  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $crowd_num = $row["crowd_num"];

?>

