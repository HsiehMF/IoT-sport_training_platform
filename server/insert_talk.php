<?php
  require "init.php";
  require "count_num_talk.php";
  require "count_num_R_detail.php";

  $UID = $_POST["UID"];
  $curDay = $_POST["curDay"];
  $content = $_POST["content"];
  
  $sql = "SELECT `Schedule`.`crowd_num` FROM (SELECT `crowd_num` FROM `Crowd_member` WHERE `UID` ='$UID') as x,  `Schedule` WHERE `Schedule`.`crowd_num` = `x`.`crowd_num` and `schedule_date` LIKE  '%$curDay%'";

  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $crowd_num = $row["crowd_num"];

  $sql1 = "INSERT INTO `Talk` (`talk_num`, `talk_UID`, `talk_time`, `talk_content`) VALUES ('$talk_num', '$UID', '$curDay', '$content')";
  
  $sql2 = "INSERT INTO `R_Detail` (`R_detail_num`, `crowd_num`, `talk_num`) VALUES ('$R_detail_num', '$crowd_num', '$talk_num');";
  
  mysqli_query($con,$sql1);
  mysqli_query($con,$sql2);
  
?>
