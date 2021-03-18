<?php

  require "../init.php";
  $UID = $_POST["UID"];
  $course_name = $_POST["course_name"];
  $selected_player = $_POST["selected_player"];
  $crowd_name = $_POST["crowd_name"];
  
  require "player_UID.php";
  
  // 先檢查群組裡是否有兩個UID一樣 , 如果有的話 , 不增加
  
  $sql = "SELECT count(UID) as TOTAL FROM `Crowd_member` WHERE `UID`='$player_UID'";
  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $TOTAL = $row["TOTAL"];
  $TOTAL = (int)$TOTAL;

  $code = 2;  // 判斷有無成功 , 讓APP端能送出訊息通知使用者
    if($total==1){
      $code = 1;
      echo $code;
    }else{
      $code = 2;
      $sql1 = "SELECT `crowd_num` FROM `Crowd` WHERE `UID`='$UID' and `course_name`='$course_name' and `crowd_name`='$crowd_name'";
      $result = mysqli_query($con,$sql1);
      $row = mysqli_fetch_array($result);
      $crowd_num = $row["crowd_num"];
  
      $sql2 = "INSERT INTO `Crowd_member` (`cm_num`, `crowd_num`, `UID`, `NAME`) VALUES ( NULL, '$crowd_num', '$player_UID', '$selected_player')";
      mysqli_query($con,$sql2);

      echo $code;
    }

?>
