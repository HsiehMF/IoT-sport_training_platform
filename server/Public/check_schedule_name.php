<?php
  require "../init.php";
  $UID = $_POST["UID"];
  $schedule_name = $_POST["schedule_name"];
  $course_name = $_POST["course_name"];
  $crowd_name = $_POST["crowd_name"];
  $nowTime = $_POST["nowTime"];
  require "transfer_course_name.php";
  require "transfer_crowd_name.php";
  
  $sql = "SELECT `schedule_num` FROM `Schedule` WHERE `Schedule`.`course_num` = '$course_num' AND `schedule_name` = '$schedule_name'";
  $result = mysqli_query($con,$sql);
  $nums = mysqli_num_rows($result);
    if($nums > 0){
      $str1 = "課表名稱已存在";
    }else{
      $str1 = "通過";
    }
  $sql = "SELECT `schedule_num` FROM `Schedule` WHERE `crowd_num` = '$crowd_num' AND `schedule_date` like '%$nowTime%'";
  $result = mysqli_query($con,$sql);
  $nums = mysqli_num_rows($result);
    if($nums >0){
      $str2 = "該群組今日已有課表";
    }
  
  echo $str1.",".$str2;

?>
