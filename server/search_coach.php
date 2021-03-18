<?php
  require "init.php";

  $sql = "SELECT `NAME`,`UNIT` FROM `User` WHERE `COACH`='1'";
  $result = mysqli_query($con,$sql);
  while ($row = mysqli_fetch_array($result)) {
       echo $row["NAME"].",".$row["UNIT"]."。";
   }

?>

