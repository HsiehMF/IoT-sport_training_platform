<?php
  
  $sql = "SELECT  `UID` FROM  `User` , (SELECT  `course_num` FROM  `Course` WHERE  `course_name` =  '$course_name' AND `UID` =  '$UID') AS x WHERE  `NAME` = '$selected_player' AND `x`.`course_num` = `User`.`course_num` ";
  
  $result = mysqli_query($con,$sql);
  $row = mysqli_fetch_array($result);
  $player_UID = $row["UID"];

?>
