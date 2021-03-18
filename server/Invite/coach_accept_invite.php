<?php

  require "../init.php";
  $UID = $_POST["UID"];
  $course_name = $_POST["course_name"];
  $selected_player = $_POST["selected_player"];
  require "transfer_course_name.php";
  require "transfer_player_UID.php";

  $sql = "UPDATE `User` SET `course_num` = '$course_num' WHERE `User`.`UID` = '$p_UID'";
  
  mysqli_query($con,$sql);

?>
