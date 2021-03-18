<?php

require "init.php";
  $UID = $_POST["UID"];
  $course_name = $_POST["course_name"];
  $selected_player = $_POST["selected_player"];

  require "player_UID.php";

  $sql1 = "SELECT `crowd_num` FROM `Crowd` WHERE `UID`='$UID' and `course_name`='$course_name' and `crowd_name`='$group_name'";
  $result = mysqli_query($con,$sql1);
  $row = mysqli_fetch_array($result);
  $crowd_num = $row["crowd_num"];


?>
