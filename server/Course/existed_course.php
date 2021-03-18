<?php
  require "../init.php";
  $UID = $_POST["UID"];

  $sql = "SELECT `course_name` FROM `Course` WHERE `UID`='$UID'";
  $result = mysqli_query($con,$sql);
  while($row = mysqli_fetch_array($result)) {
      echo $row["course_name"];
      echo ",";
  }
?>
