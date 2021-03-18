<?php  
  require "init.php";  
  $sql_query = "SELECT `item_id` FROM  `Item` WHERE 1 ";  
  $result = mysqli_query($con,$sql_query);
  $num = mysqli_num_rows($result);
?>
