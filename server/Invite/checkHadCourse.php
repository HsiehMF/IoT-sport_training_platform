<?php

  require "../init.php";
  $UID = $_POST["UID"];
  $a_course_name = $_POST["course_name"];

  $sql = "SELECT User.`course_num`,`course_name` FROM `User`,`Course` WHERE User.`UID`='$UID' AND User.`course_num` = Course.`course_num`";
  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $course_num = $row["course_num"];
  $course_name = $row["course_name"];
   
  $UNDER = "/UNDERREVIEW/";
    if (empty($course_num)){
      echo "無社團狀態";
    }else if (preg_match($UNDER, $course_num)){
      echo "審核中";
    }else {
      if ($a_course_name == $course_name){  echo "退出社團";  }
      else {  echo "您已加入其他社團";  }
    }

?>
