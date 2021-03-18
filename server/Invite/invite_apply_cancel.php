<?php

  require "../init.php";
  $UID = $_POST["UID"];

  $sql = "UPDATE `User` SET `course_num` = '' WHERE  `User`.`UID` = '$UID'";
  $result = mysqli_query($con,$sql);

?>

