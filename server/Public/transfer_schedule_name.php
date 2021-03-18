<?php
  
  $sql = "SELECT `schedule_num` FROM `Schedule` WHERE `schedule_name`='$schedule_name' and `schedule_date` like '%$date%'";
  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $schedule_num = $row["schedule_num"];

?> 
