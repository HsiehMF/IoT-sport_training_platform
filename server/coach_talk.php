<?php

  require "init.php";
  $UID = $_POST["UID"];
  $crowd_name = $_POST["crowd_name"];
  require "transfer_crowd_name.php";  

  $sql = "SELECT `Talk`.`talk_UID`,User.`NAME`,Talk.`talk_content` FROM `R_Detail`,`User`,`Talk` WHERE `crowd_num` = '$crowd_num' and  Talk.`talk_num` = R_Detail.`talk_num` and User.`UID` = Talk.`talk_UID` ORDER BY `Talk`.`talk_num`";

  $result = mysqli_query($con,$sql);
    while ($row = mysqli_fetch_array($result)) {
       echo $row["talk_UID"]. "◆" .$row["NAME"]." : ".$row["talk_content"];
       echo "◎";
    }
?>
