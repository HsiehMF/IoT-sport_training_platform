<?php
  require "../init.php";
  $schedule_name = $_POST["schedule_name"];
  $date = $_POST["date"];
  require "transfer_schedule_name.php";
  
  $sql = "SELECT `Line`.`schedule_num`, `Item`.`item_id`,`item_name`,`item_sub_name`,`item_times`,`item_time`,`item_note` FROM `Item`,`Line` WHERE `Item`.`item_id`= `Line`.`item_id` and `Line`.`schedule_num` = '$schedule_num'";
  
  $result = mysqli_query($con,$sql);
    while ($row = mysqli_fetch_array($result)) {
      echo $row["schedule_num"].",".$row["item_id"].",".$row["item_name"].",".$row["item_sub_name"].",".$row["item_times"].",".$row["item_time"].",".$row["item_note"]."&";
  }

?>
