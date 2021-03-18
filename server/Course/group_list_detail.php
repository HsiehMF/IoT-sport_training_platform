<?php
  require "../init.php";
  $UID = $_POST["UID"];
  $course_name = $_POST["course_name"];
  $group_name = $_POST["group_name"];

  $sql = "SELECT `NAME`,`mark` FROM `Crowd_member`,(SELECT `crowd_num` FROM `Crowd` WHERE `UID`='$UID' and `crowd_name`='$group_name' and `course_name` = '$course_name')as x WHERE `x`.`crowd_num` = `Crowd_member`.`crowd_num`";

  $result = mysqli_query($con,$sql);
    while($row = mysqli_fetch_array($result)){
      echo $row["NAME"].",".$row["mark"]."&";
    }
