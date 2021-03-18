<?php
  /* 當套用課表的修改中有新增項目 */
  require "../init.php";
  require "../count.php";  /* 查看目前有幾筆資訊 */
  $schedule_num = $_POST["schedule_num"];
  $main_item = $_POST["main_item"];
  $sub_item = $_POST["sub_item"];
  $item_times = $_POST["item_times"];
  $item_time = $_POST["item_time"];
  $item_note = $_POST["item_note"];
  $num = $num + 1; 	   /* 目前筆數編號 +1 為新增筆數的編號 */ 
  
  $sql1 = "INSERT INTO  `webappdb`.`Item` (`item_id` ,`item_name` ,`item_sub_name` ,`item_times` ,`item_time` ,`item_note`) VALUES ( $num , '$main_item', '$sub_item', '$item_times', '$item_time', '$item_note')";
  $sql2 = "INSERT INTO `Line` (`line_num` ,`schedule_num` ,`item_id`)VALUES(NULL , '$schedule_num', '$num')";

  mysqli_query($con,$sql1);
  mysqli_query($con,$sql2);

?>
