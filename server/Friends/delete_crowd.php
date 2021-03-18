<?php
  require "../init.php";
  $UID = $_POST["UID"];
  $delete_crowd_name = $_POST["crowd_name"];
  $course_name = $_POST["course_name"];
  
  $sql = "DELETE FROM `Crowd` WHERE `crowd_name` = '$delete_crowd_name' and `UID` = '$UID' and `course_name` = '$course_name'";
  mysqli_query($con,$sql);

?>
