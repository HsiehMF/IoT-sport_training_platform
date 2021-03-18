<?php

  require "../init.php";
  $main_item = $_POST["main_item"];
  $sub_item = $_POST["sub_item"];
  $item_times = $_POST["item_times"];
  $item_time = $_POST["item_time"];
  $item_note = $_POST["item_note"];
  $item_id = $_POST["item_id"];
  
  echo $main_item.$sub_item.$item_times;  

  $sql = "UPDATE `Item` SET `item_name` = '$main_item' ,`item_sub_name` = '$sub_item' ,`item_times` = '$item_times',`item_time` = '$item_time',`item_note` = '$item_note' WHERE `Item`.`item_id` = '$item_id'";
  
  mysqli_query($con,$sql);

?>
