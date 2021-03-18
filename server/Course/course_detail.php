<?php
  require "../init.php";
  $course_name = $_POST["course_name"];
  $UID = $_POST["UID"];
  
  require "course_detail_ft1.php";
  $sql = "SELECT  `course_name` ,  `course_date` ,  `User`.`NAME` ,  `course_info` FROM  `Course` ,  `User` WHERE  `User`.`UID` =  `Course`.`UID` AND `Course`.`UID`='$UID' AND `course_name` =  '$course_name'";
  $result = mysqli_query($con,$sql);
  while($row = mysqli_fetch_array($result)) {
      echo $row["course_name"]."§".$row["course_date"]."§".$row["NAME"]."§".$row["course_info"]."§".$people_mount;
  }


?>
