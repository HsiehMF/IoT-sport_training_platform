<?php

  require "../init.php";
  $course_name = $_POST["course_name"];
  $UID = $_POST["UID"];
  
  $sql1 = "SELECT `course_num` FROM `Course` WHERE Course.`course_name`='$course_name' and Course.`UID`='$UID'";
  $result1 = mysqli_query($con,$sql1);
  $row1 = mysqli_fetch_array($result1);
  $course_num = $row1["course_num"];
 
  $sql2 = "SELECT `UID`,`NAME` FROM `User` WHERE `course_num` = '$course_num' AND `COACH`='0' AND `UID` not in (SELECT `UID` from `Crowd_member` where 1)";

  $result2 = mysqli_query($con,$sql2);
    while($row2 = mysqli_fetch_array($result2)) {
      echo $row2["NAME"];
      echo ",";
    }
?>
