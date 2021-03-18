<?php

  require "../init.php";
  $UID = $_POST["UID"];
  $schedule_name = $_POST["schedule_name"];
  $course_name = $_POST["course_name"];

  require "transfer_course_name.php";   // 得到 course_num

  $sql = "SELECT `schedule_num` FROM `Schedule` WHERE `schedule_name`='$schedule_name' and `course_num`='$course_num'";
  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $schedule_num = $row["schedule_num"]; // 得到 schedule_num
  
  echo $schedule_num;

?>
