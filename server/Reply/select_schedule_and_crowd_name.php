<?php

  require "../init.php";
  $UID = $_POST["UID"];  // 用以轉換 course_name to course_num
  $date = $_POST["date"];
  $course_name = $_POST["course_name"];
  require "transfer_course_name.php";

  $sql = "SELECT `schedule_name`, `crowd_name` FROM `Schedule`,`Crowd` WHERE `course_num`='$course_num' and `schedule_date` like '%$date%' and `Schedule`.`crowd_num` = `Crowd`.`crowd_num`";

  $result = mysqli_query($con,$sql);
    while($row = mysqli_fetch_array($result)){
      echo $row["schedule_name"].",".$row["crowd_name"]."&";
    }
?>
