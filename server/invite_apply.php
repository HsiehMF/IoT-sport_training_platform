<?php
  require "init.php";
  
  $course_name = $_POST["course_name"];
  $NAME = $_POST["NAME"];
  $UNIT = $_POST["UNIT"];
  
  $sql1 = "SELECT `Course`.`course_num` FROM `Course`,`User` WHERE `User`.`NAME` = '$NAME' and `User`.`UNIT` = '$UNIT' and `Course`.`course_name` = '$course_name'";
  $result1 = mysqli_query($con,$sql1);
  $row1 = mysqli_fetch_array($result1);
  $course_num = $row1["course_num"];

  $sql2 = "SELECT count(`User`.`course_num`)as mount FROM `User` WHERE `User`.`course_num`='$course_num' AND `COACH`='0'"; 
  $result2 = mysqli_query($con, $sql2);
  $row2 = mysqli_fetch_array($result2);
  $mount = $row2["mount"]; 

  $sql3 = "SELECT `Course`.`course_num`,`course_name`,`course_date`,`course_info` FROM `Course`,`User` WHERE `course_name`='$course_name' and `User`.`NAME`='$NAME' and `User`.`UNIT`='$UNIT' and `User`.`UID` = `Course`.`UID`";
  
  $result3 = mysqli_query($con,$sql3);
    while ($row3 = mysqli_fetch_array($result3)) {
       echo $row3["course_num"].",".$row3["course_name"].",".$row3['course_date'].",".$row3["course_info"].",".$mount;
    }

?>

