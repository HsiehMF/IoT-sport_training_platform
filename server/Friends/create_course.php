<?php
  require "../init.php";
  require "count_num_course.php";

  $UID = $_POST["UID"];
  $new_course_name = $_POST["new_course_name"];
  $new_course_info = $_POST["new_course_info"];
  $crowd_amount = $_POST["crowd_amount"];
  $curDay = $_POST["curDay"];
   
  $sql = "INSERT INTO `Course` (`course_num`, `course_name`, `UID`, `course_date`, `course_info`) VALUES ( '$course_num', '$new_course_name', '$UID', '$curDay', '$new_course_info')";
  mysqli_query($con,$sql);

  $crowd_amount = (int)$crowd_amount;
  $eng = 'A';

  for($i = 0; $i < $crowd_amount; $i++){
    $sql1 = "INSERT INTO `Crowd` (`crowd_num`, `UID`, `course_name`, `crowd_name`, `crowd_info`) VALUES (NULL, '$UID', '$new_course_name', '$new_course_name 群組$eng', NULL);";
    mysqli_query($con,$sql1);
    $eng++;
  }
?>
