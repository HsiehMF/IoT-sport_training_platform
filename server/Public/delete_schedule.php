<?php
  require "../init.php";
  $schedule_id = $_POST["schedule_id"];

  $sql = "DELETE FROM `Schedule` WHERE `schedule_num` = '$schedule_id'";
  mysqli_query($con,$sql);

?>
