<?php
  require "../init.php";
  require "count_num_course.php";

  $UID = $_POST["UID"];
  $new_course_name = $_POST["new_course_name"];
  $new_course_info = $_POST["new_course_info"];
  $curDay = $_POST["curDay"];
   
  echo $UID.$new_course_name.$new_course_info.$curDay;
  $sql = "INSERT INTO `Course` (`course_num`, `course_name`, `UID`, `course_date`, `course_info`) VALUES ( '$course_num', '$new_course_name', '$UID', '$curDay', '$new_course_info')";
  mysqli_query($con,$sql);
?>
