<?php
  require "../init.php";
  $UID = $_POST["UID"];
  $schedule_name = $_POST["schedule_name"];
  $crowd_name = $_POST["crowd_name"];
  $course_name = $_POST["course_name"];
  $nowTime = $_POST["nowTime"];

  require "count_num_schedule.php"; 
  require "transfer_course_name.php";
  require "transfer_crowd_name.php";
  
  $apply = "[套]";
  $schedule_name = $apply.$schedule_name;
 
  $sql = "INSERT INTO `Schedule` (`schedule_num`, `course_num`, `schedule_name`, `schedule_date`,`save`, `satisfaction`, `degree_of_completion`, `fatigue`, `schedule_info`, `crowd_num`, `Estimate`) VALUES ('$schedule_num', '$course_num', '$schedule_name', '$nowTime','0', '0', '0', '0', '預設值', '$crowd_num', '')";
  
  mysqli_query($con,$sql);
  echo $schedule_num;	// 透過 BackgroundTask 讓程式知道 schedule_num
 
?>
