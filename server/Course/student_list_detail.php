<?php
  require "../init.php";
  $course_name = $_POST["course_name"];
  $UID = $_POST["UID"];
  $player_name = $_POST["player_name"];
  
   $sql = "SELECT `course_num` FROM `Course` WHERE Course.`course_name`='$course_name' and Course.`UID`='$UID'";
  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $number = $row["course_num"];

  $sql1 = "SELECT `NAME`,`UNIT`,`BIRTHDAY`,`GENDER`,`INFO`,`course_name` FROM `User`,`Course` WHERE `Course`.`course_num` = `User`.`course_num` and `Course`.`course_num` = '2'  and `User`.`NAME` = '$player_name'";
  $result1 = mysqli_query($con,$sql1);
  while($row1 = mysqli_fetch_array($result1)) {
    echo $row1["NAME"].",".$row1["UNIT"].",".$row1["BIRTHDAY"].",".$row1["GENDER"].",".$row1["INFO"].",".$row1["course_name"];
  }

  
?>
