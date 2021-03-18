<?php

  require "../init.php";
  $course_num = $_POST["course_num"];
  $coach_name = $_POST["coach_name"];
  $UID = $_POST["UID"];
  $under_review = "UNDERREVIEW";

  $sql = "UPDATE `User` SET  `course_num` =  '$course_num,$under_review' WHERE  `User`.`UID` = '$UID'";

  $result = mysqli_query($con,$sql);

?>

