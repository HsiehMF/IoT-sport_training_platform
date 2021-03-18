<?php 

  $sql = "SELECT `course_num` FROM `Course` WHERE `course_name`='$course_name' and `UID`='$UID'";
  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $course_num = $row["course_num"];

?>
