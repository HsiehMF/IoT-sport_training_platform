<?php  
  require "init.php";  
  require "count.php";  /* 查看目前有幾筆資訊 */
  require "count_num_schedule.php";

  $main_item = $_POST["main_item"];
  $sub_item = $_POST["sub_item"];
  $times = $_POST["times"];
  $item_time = $_POST["item_time"];
  $num = $num + 1;      /* 目前筆數編號 +1 為新增筆數的編號 */
  
  $sql_query = "INSERT INTO  `webappdb`.`Item` (`item_id` ,`item_name` ,`item_sub_name` ,`item_times` ,`item_time`) VALUES ( $num ,  '$main_item',  '$sub_item',  '$times',  '$item_time')";
  $sql_query1 = "INSERT INTO  `webappdb`.`Line` (`line_num` ,`schedule_num` ,`item_id`)VALUES (NULL ,  '$schedule_num',  '$num')";

  $result = mysqli_query($con,$sql_query);
  $result1 = mysqli_query($con,$sql_query1);
  
/* 以後可能要修改，因為刪除過後編號可能會重複，用 LIMIT 方式找最後一筆的值 +1 */

?>
