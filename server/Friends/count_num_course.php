<?php
  require "../init.php";

  $sql_query = "SELECT course_num FROM  `Course` ORDER BY  `Course`.`course_num` DESC LIMIT 1";
  $result = mysqli_query($con,$sql_query);
  $row = mysqli_fetch_array($result);
  $course_num = $row["course_num"] + 1;

?>

