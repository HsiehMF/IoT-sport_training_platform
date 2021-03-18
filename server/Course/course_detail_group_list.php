<?php
  require "../init.php";
  $UID = $_POST["UID"];
  $course_name = $_POST["course_name"];

  $sql = "SELECT `crowd_name` FROM `Crowd` WHERE `UID`='$UID' and `course_name`='$course_name'";
  $result = mysqli_query($con,$sql);
  while($row = mysqli_fetch_array($result)) {
    echo $row["crowd_name"];
    echo ",";
  }

?>  
