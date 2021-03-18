<?php
  require "../init.php";
  $student_delete_list = $_POST["student_delete_list"];
  $UID = $_POST["UID"];
  $course_name = $_POST["course_name"];
  $group_name = $_POST["group_name"];

  $sql1 = "SELECT `crowd_num` FROM `Crowd` WHERE `UID`='$UID' and `crowd_name`='$group_name'";
  $result = mysqli_query($con,$sql1);
  $row = mysqli_fetch_array($result);
  $crowd_num = $row["crowd_num"];
  
  $sql2 = "DELETE FROM `Crowd_member` WHERE `crowd_num`='$crowd_num' and `NAME`='$student_delete_list'";
  mysqli_query($con,$sql2);

?>
