<?php
  require "init.php";

  $UID = $_POST["UID"];
  $course_num = $_POST["course_num"];
  $coach_name = $_POST["coach_name"];
   
  $sql = "SELECT `course_num` FROM `User` WHERE UID='$UID'";
  $result = mysqli_query($con,$sql);
  while ($row = mysqli_fetch_array($result)) {
    if($row["course_num"]=="$course_num,$coach_name,審核中")
      echo "審核中";
    if($row["course_num"]=="$course_num")
      echo "審核通過";
    if($row["course_num"]=="")
      echo "尚未選擇";
  }
  
?>

