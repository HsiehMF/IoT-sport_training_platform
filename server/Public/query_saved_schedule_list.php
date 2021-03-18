<?php

  require "../init.php";
  $UID = $_POST["UID"];
  $course_name = $_POST["course_name"];
  $selected = $_POST["selected"]; 
  require "transfer_course_name.php"; 
  
    if ($selected=="依日期分類 (新到舊)" || $selected=="預設"){
      $sql = "SELECT `schedule_num`,`schedule_name`,`schedule_date`,`Course`.`course_name`,`crowd_name` FROM `Schedule`,`Crowd`,`Course` WHERE `Schedule`.`course_num`='$course_num' and `save`='1' and `Crowd`.`crowd_num` = `Schedule`.`crowd_num` and `Course`.`course_num` = `Schedule`.`course_num` ORDER BY `Schedule`.`schedule_date` DESC";
    }else if ($selected == "依日期分類 (舊到新)"){
      $sql = "SELECT `schedule_num`,`schedule_name`,`schedule_date`,`Course`.`course_name`,`crowd_name` FROM `Schedule`,`Crowd`,`Course` WHERE `Schedule`.`course_num`='$course_num' and `save`='1' and `Crowd`.`crowd_num` = `Schedule`.`crowd_num` and `Course`.`course_num` = `Schedule`.`course_num` ORDER BY `Schedule`.`schedule_date` ASC";
    }else if ($selected == "依群組分類 (大到小)"){
      $sql = "SELECT `schedule_num`,`schedule_name`,`schedule_date`,`Course`.`course_name`,`crowd_name` FROM `Schedule`,`Crowd`,`Course` WHERE `Schedule`.`course_num`='$course_num' and `save`='1' and `Crowd`.`crowd_num` = `Schedule`.`crowd_num` and `Course`.`course_num` = `Schedule`.`course_num` ORDER BY `crowd_name` DESC";
    }else if ($selected == "依群組分類 (小到大)"){
      $sql = "SELECT `schedule_num`,`schedule_name`,`schedule_date`,`Course`.`course_name`,`crowd_name` FROM `Schedule`,`Crowd`,`Course` WHERE `Schedule`.`course_num`='$course_num' and `save`='1' and `Crowd`.`crowd_num` = `Schedule`.`crowd_num` and `Course`.`course_num` = `Schedule`.`course_num` ORDER BY `crowd_name` ASC";
    }

  $result = mysqli_query($con,$sql);
    while($row = mysqli_fetch_array($result)){
      echo $row["schedule_num"].",".$row["schedule_name"].",".$row["schedule_date"].",".$row["course_name"].",".$row["crowd_name"]."&";
    }

?>
