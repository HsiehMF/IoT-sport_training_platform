<?php
  
  $sql = "SELECT `UID` FROM `User` WHERE `NAME`='$selected_player' and `course_num` like '%$course_num,審核中%'";
  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $p_UID = $row["UID"];

?>

