<?php
  /* 待審核選手 */
  require "../init.php";
  $UID = $_POST["UID"];
  $course_name = $_POST["course_name"];
  require "transfer_course_name.php";
  
  $sql = "SELECT `NAME` FROM `User` WHERE `course_num` like '%$course_num,審核中%'";
  $result = mysqli_query($con,$sql);
  while ($row = mysqli_fetch_array($result)) {
    echo $row["NAME"];
    echo ",";
  }

?>
