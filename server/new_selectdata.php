 <?php  
   require "init.php";  
   $schedule_name = $_POST["schedule_name"];

   $sql = "SELECT `item_name`,`item_sub_name`,`item_times`,`item_time` FROM `Item`,`Line`,(SELECT `schedule_num` FROM `Schedule` WHERE `schedule_name`='$schedule_name')as x WHERE x.`schedule_num` = Line.`schedule_num`  and Line.`item_id` = Item.`item_id`";
  
   $result = mysqli_query($con,$sql);
   while($row = mysqli_fetch_array($result)){
       echo $row["item_name"]."-".$row["item_sub_name"]."  ,  ".$row["item_times"]."次  ,  ".$row["item_time"]."秒</br>";
   }

 
 ?>
