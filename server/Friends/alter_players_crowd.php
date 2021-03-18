<?php
  require "../init.php";
  $alter_crowd_name = $_POST["alter_crowd_name"];
  $course_name = $_POST["course_name"];
  $crowd_name = $_POST["crowd_name"];
  $NAME = $_POST["NAME"];

  // 查詢目前所屬群組的編號

  $query1 = "SELECT `crowd_num` FROM `Crowd` WHERE `course_name`='$course_name' and `crowd_name`='$crowd_name'";
  $result = mysqli_query($con,$query1);
  $row = mysqli_fetch_array($result);
  $crowd_num = $row["crowd_num"];
  echo "crowd_num : ".$crowd_num; 

  // 查詢更換群組的編號

  $query2 = "SELECT `crowd_num` FROM `Crowd` WHERE `course_name`='$course_name' and `crowd_name`='$alter_crowd_name'";
  $result = mysqli_query($con,$query2);
  $row = mysqli_fetch_array($result);
  $alter_crowd_num = $row["crowd_num"];
  echo "alter_crowd_num : ".$alter_crowd_num;

  $sql = "UPDATE `Crowd_member` SET `crowd_num` = '$alter_crowd_num', `mark` = 'null' WHERE `crowd_num` ='$crowd_num' and `NAME` = '$NAME'";
 
   mysqli_query($con,$sql);

?>
