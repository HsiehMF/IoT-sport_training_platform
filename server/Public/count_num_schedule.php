<?php 
  require "../init.php";
  
  $sql = "SELECT `schedule_num` FROM `Schedule` ORDER BY `Schedule`.`schedule_num` DESC LIMIT 1";
  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $schedule_num = $row["schedule_num"] + 1;

?>
