<?php  
  require "init.php";
  require "count_num_schedule.php";

  $schedule_title = $_POST["schedule_title"];
  $curTime = $_POST["curTime"];
  $course_num = $_POST["course_num"];
  $group_num = $_POST["group_num"];

  $sql_query = "INSERT INTO  `webappdb`.`Schedule` (`schedule_num` ,`course_num` ,`schedule_name` ,`schedule_date` ,`satisfaction` ,`degree_of_completion` ,`fatigue` ,`schedule_info` ,`group_num`) VALUES ('$schedule_num',  '$course_num',  '$schedule_title',  '$curTime',  '',  '',  '',  '里約奧運',  '$group_num');";

  $result = mysqli_query($con,$sql_query);

?>
