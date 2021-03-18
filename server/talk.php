<?php
  require "init.php";
  
  $UID = $_POST["UID"];
  $curDay = $_POST["curDay"];  
//    $UID =10;
//    $curDay="2016-10-31";  

 $sql = "SELECT `Talk`.`talk_UID`,`User`.`NAME`,Talk.`talk_content` FROM (SELECT Schedule.`crowd_num` FROM (SELECT `crowd_num` FROM `Crowd_member` WHERE UID='$UID')as x ,`Schedule` WHERE Schedule.`crowd_num` = x.`crowd_num` and Schedule.`schedule_date` like '%$curDay%')as x,`R_Detail`,`Talk`,`User` WHERE x.`crowd_num` = R_Detail.`crowd_num` and Talk.`talk_num` = R_Detail.`talk_num` and User.`UID` = Talk.`talk_UID`";
// echo $sql;
  $result = mysqli_query($con,$sql);
    while ($row = mysqli_fetch_array($result)) {
       echo $row["talk_UID"]. "◆" .$row["NAME"]." : ".$row["talk_content"];
       echo "◎";
    }
  
?>
