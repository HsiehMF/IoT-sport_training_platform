<?php
  require "init.php";

  $UNIT = $_POST["UNIT"];
  $NAME = $_POST["NAME"];

  $sql = "SELECT `course_name` FROM (SELECT `UID` FROM `User` WHERE `NAME`='$NAME' and UNIT='$UNIT') as x,`Course` WHERE `Course`.`UID`= x.UID";
  $result = mysqli_query($con,$sql);
  while ($row = mysqli_fetch_array($result)) {
       echo $row["course_name"]."。";
   }

?>

