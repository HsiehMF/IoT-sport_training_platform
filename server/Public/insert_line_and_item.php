<?php  
  require "../init.php";  
  require "../count.php";  /* 查看目前有幾筆資訊 */
  $schedule_num = $_POST["schedule_num"];
  $main_item = $_POST["main_item"];
  $sub_item = $_POST["sub_item"];
  $item_times = $_POST["item_times"];
  $time = $_POST["time"];
  $note = $_POST["note"];
  $num = $num + 1;      /* 目前筆數編號 +1 為新增筆數的編號 */
  
  $sql_query1 = "INSERT INTO  `webappdb`.`Item` (`item_id` ,`item_name` ,`item_sub_name` ,`item_times` ,`item_time` ,`item_note`) VALUES ( $num ,  '$main_item',  '$sub_item',  '$item_times',  '$time',  '$note')";
  $sql_query2 = "INSERT INTO  `webappdb`.`Line` (`line_num` ,`schedule_num` ,`item_id`)VALUES (NULL ,  '$schedule_num',  '$num')";

  $result1 = mysqli_query($con,$sql_query1);
  $result2 = mysqli_query($con,$sql_query2);
  
/* 以後可能要修改，因為刪除過後編號可能會重複，用 LIMIT 方式找最後一筆的值 +1 */

?>
