<?php
  require "../init.php";
  $UID = $_POST["UID"];
  $course_name = $_POST["course_name"];
  $name = $_POST["name"];
  $info = $_POST["info"];

  $sql = "UPDATE `Course` SET  `course_name` =  '$name', `course_info` = '$info' WHERE `course_name` = '$course_name' and `UID` = '$UID'";
  mysqli_query($con,$sql);


?>
