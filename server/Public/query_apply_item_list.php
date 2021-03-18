<?php
  /* 關於查詢套用計畫中的訓練細項 */
  require "../init.php";
  $UID = $_POST["UID"];
  $schedule_name = $_POST["schedule_name"];
  $course_name = $_POST["course_name"];
  
  require "transfer_course_name.php";   // 得到 course_num

  $sql = "SELECT `schedule_num` FROM `Schedule` WHERE `schedule_name`='$schedule_name' and `course_num`='$course_num'";
  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $schedule_num = $row["schedule_num"]; // 得到 schedule_num 
  
  $sql = "SELECT `Line`.`schedule_num`,`Item`.`item_id`,`item_name`,`item_sub_name`,`item_times`,`item_time`,`item_note` FROM `Item`,`Line` WHERE `Item`.`item_id`= `Line`.`item_id` and `Line`.`schedule_num` = '$schedule_num'";
  
  $result = mysqli_query($con,$sql);
  while ($row = mysqli_fetch_array($result)) {
    echo $row["schedule_num"].",".$row["item_id"].",".$row["item_name"].",".$row["item_sub_name"].",".$row["item_times"].",".$row["item_time"].",".$row["item_note"]."&";
  }

?>
