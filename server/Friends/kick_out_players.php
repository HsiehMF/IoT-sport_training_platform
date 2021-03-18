<?php
  require "../init.php";
  $course_name = $_POST["course_name"];
  $crowd_name = $_POST["crowd_name"];
  $NAME = $_POST["NAME"];
  
  $query = "SELECT `crowd_num` FROM `Crowd` WHERE `course_name`='$course_name' and `crowd_name`='$crowd_name'";
  $result = mysqli_query($con,$query);
  $row = mysqli_fetch_array($result);
  $crowd_num = $row["crowd_num"];
  
  $sql = "DELETE FROM `Crowd_member` WHERE `crowd_num` = '$crowd_num' and `NAME` = '$NAME'";
  mysqli_query($con,$sql);

?>
