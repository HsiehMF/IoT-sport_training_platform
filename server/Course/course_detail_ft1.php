<?php
  
  $sql = "SELECT `course_num` FROM `Course` WHERE `course_name`='$course_name' AND `UID`='$UID'";
  
  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $number = $row["course_num"];

  $sql1 = "SELECT count(`User`.`course_num`)as mount FROM `User` WHERE `User`.`course_num`='$number' AND `COACH`='0'";
  $result1 = mysqli_query($con,$sql1);
  $row1 = mysqli_fetch_array($result1);
  $people_mount = $row1["mount"];

?>
