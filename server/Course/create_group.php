<?php
  require "../init.php";
  require "count_num_crowd.php";
  $UID = $_POST["UID"];
  $group_name = $_POST["group_name"];
  $selected_player = $_POST["selected_player"];
  $course_name = $_POST["course_name"];  // 判斷選手 UID 的條件之一
  require "player_UID.php";
  /* 
  $UID =2;
  $group_name ='HIA';
  $selected_player='高嘉妤';
  $course_name = '屏東扣釘特遣隊';
  */
  
  $sql1 = "SELECT `crowd_num` FROM `Crowd` WHERE `UID`='$UID' and `crowd_name` = '$group_name'";
  $result = mysqli_query($con,$sql1);
  $nums = mysqli_num_rows($result);

    if($nums > 0){

      echo "群組名稱已存在";

    }else{
  
      $sql2 = "INSERT INTO `Crowd` (`crowd_num`, `UID`,`course_name`, `crowd_name`, `crowd_info`) VALUES ('$crowd_num', '$UID', '$course_name', '$group_name', '在這裡輸入簡介...')";
      $sql3 = "INSERT INTO `Crowd_member` (`cm_num` ,`crowd_num` ,`UID` ,`NAME`) VALUES (NULL ,  '$crowd_num',  '$player_UID',  '$selected_player')";

      mysqli_query($con,$sql2);
      mysqli_query($con,$sql3);

    }

?>
