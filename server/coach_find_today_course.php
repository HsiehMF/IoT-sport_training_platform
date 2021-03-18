<?php
  
  require "init.php";
  $UID = $_POST["UID"];
  $date = $_POST["date"];
  $crowd_name = $_POST["crowd_name"];
  require "transfer_crowd_name.php";  

  $sql = "SELECT `schedule_num` FROM `Schedule` WHERE `crowd_num` = '$crowd_num' and `schedule_date` like '%$date%'";
  
  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $schedule_num = $row["schedule_num"];

  $sql = "SELECT `item_name`,`item_sub_name`,`item_times`,`item_time` FROM `Item`,`Line` WHERE `Item`.`item_id` = `Line`.`item_id` and `Line`.`schedule_num` = '$schedule_num'";

  $result = mysqli_query($con,$sql);
  $i = 1;
    while ($row = mysqli_fetch_array($result)) {
      echo $i.".";
      echo $row["item_name"].",".$row["item_sub_name"].",".$row["item_times"]."次,".$row["item_time"]."秒<".$row["judgement"]."。";
      $i=$i+1;
    }

?>
