<?php

  require "../init.php";
  $UID = $_POST["UID"];
  $course_name = $_POST["course_name"];
  $crowd_name = $_POST["crowd_name"];
  $date = $_POST["date"];
  
  require "transfer_course_name.php";
  require "transfer_crowd_name.php";
  
  $sql = "SELECT `schedule_num`,`schedule_name` FROM `Schedule` WHERE `course_num`='$course_num' and `crowd_num`='$crowd_num' and `schedule_date` like '%$date%'";
  
  $result = mysqli_query($con,$sql);
  $nums = mysqli_num_rows($result);
    if ($nums == 0){
        echo "今日無訓練課表";
    }else{
      while ($row = mysqli_fetch_array($result)) {
        echo $row["schedule_num"].",".$row["schedule_name"];
      }
    }

?>
