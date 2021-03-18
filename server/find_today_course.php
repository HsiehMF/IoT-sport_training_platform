<?php
  require "init.php";
  
  $UID = $_POST["UID"];
  $curDay = $_POST["curDay"];
//  $UID = 2;
//  $curDay ="2016-10-31";  

  $sql = "SELECT Item.item_name,Item.item_sub_name,Item.item_times,Item.item_time,Line.judgement FROM (SELECT crowd_num FROM `Crowd_member` WHERE UID='$UID') as x ,`Schedule`,`Line`,`Item` WHERE Schedule.crowd_num = x.crowd_num and Schedule.schedule_num = Line.schedule_num and Line.item_id = Item.item_id and `schedule_date` LIKE  '%$curDay%'";

  $result = mysqli_query($con,$sql);
  $i = 1;
    while ($row = mysqli_fetch_array($result)) {
      echo $i.".";
      echo $row["item_name"].",".$row["item_sub_name"].",".$row["item_times"]."次,".$row["item_time"]."秒<".$row["judgement"]."。";
      $i=$i+1;
    } 
?>
