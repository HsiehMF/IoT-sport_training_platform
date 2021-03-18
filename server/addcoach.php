<?php
  require "init.php";
  $selected_course_num = $_POST["course_name"];
  $student_name = $_POST["student_name"];
  $UID = $_POST["UID"];
 
  require "selectfromstudent_1.php";  // 找到課程編號

  $sql = "UPDATE User SET course_num = '$course_num' WHERE NAME = '$student_name'";
  $result = mysqli_query($con,$sql);
?>




