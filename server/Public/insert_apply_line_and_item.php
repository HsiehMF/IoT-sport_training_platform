<?php
  
  require "../init.php";
  $old_schedule_num = $_POST["old_schedule_num"];
  $schedule_num = $_POST["schedule_num"];

  $sql = "SELECT `item_id` FROM `Line` WHERE `schedule_num` = '$old_schedule_num'";

  $result = mysqli_query($con,$sql);
    while ($row = mysqli_fetch_array($result)){
      $id = $row["item_id"].",";
      $str = $str.$id;
    }

  $array = explode(",", $str);
  $j = (int)count($array)-1; // count 陣列數量 , 最後一值為空 , 所以數量減一
    for( $i=0 ; $i<$j ; $i++ ){
      $item_id = $array[$i];
      $sql = "INSERT INTO  `webappdb`.`Line` (`line_num` ,`schedule_num` ,`item_id`)VALUES (NULL ,  '$schedule_num',  '$item_id')";
      mysqli_query($con,$sql);
    }

?> 
  
