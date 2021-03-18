<?php
  require "../init.php";
  $course_name = $_POST["course_name"];
  $UID = $_POST["UID"];
  
  $sql = "DELETE FROM `Course` WHERE `course_name`='$course_name' and `UID`='$UID'";
  mysqli_query($con,$sql);

?>
