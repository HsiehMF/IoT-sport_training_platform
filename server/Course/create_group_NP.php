<?php

  require "../init.php";
  require "count_num_crowd.php";
  $UID = $_POST["UID"];
  $group_name = $_POST["group_name"];
  $course_name = $_POST["course_name"];  // 判斷選手 UID 的條件之一
  require "player_UID.php";
  
  $sql1 = "SELECT `crowd_num` FROM `Crowd` WHERE `UID`='$UID' and `crowd_name` = '$group_name'";
  $result = mysqli_query($con,$sql1);
  $nums = mysqli_num_rows($result);

    if($nums > 0){

      echo "群組名稱已存在";

    }else{
  
      $sql2 = "INSERT INTO `Crowd` (`crowd_num`, `UID`,`course_name`, `crowd_name`, `crowd_info`) VALUES ('$crowd_num', '$UID', '$course_name', '$group_name', '在這裡輸入簡介...')";

      mysqli_query($con,$sql2);

    }

?>
