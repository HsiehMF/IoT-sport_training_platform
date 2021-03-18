<?php
  require "init.php";
  $student_name = $_POST["student_name"];
  
  $sql = "UPDATE  `webappdb`.`User` SET  `course_num`='' WHERE  `User`.`NAME` = '$student_name'";
  $result = mysqli_query($con,$sql);
?>


