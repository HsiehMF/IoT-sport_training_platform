<?php

  require "../init.php";
  $UID = $_POST["UID"];
  $date = $_POST["date"];
  
  $sql = "SELECT `schedule_num`,`schedule_name`,`schedule_date`,`Course`.`course_name`, `Schedule`.`course_num`,`crowd_name`, `Schedule`.`crowd_num` FROM (SELECT `course_num` FROM `Course` WHERE `UID` = '$UID') as x ,`Schedule`,`Course`,`Crowd` WHERE `schedule_date` like '%$date%' and  `x`.`course_num` = `Schedule`.`course_num` and `Course`.`course_num` = `Schedule`.`course_num` and `Crowd`.`crowd_num` = `Schedule`.`crowd_num`";
  
  $result = mysqli_query($con,$sql);
  $nums = mysqli_num_rows($result);
    if ($nums == 0){
	echo "今日無訓練課表";
    }else{
      while($row = mysqli_fetch_array($result)){
        echo $row["schedule_num"].",".$row["schedule_name"].",".$row["schedule_date"].",".$row["course_name"].",".$row["course_num"].",".$row["crowd_name"].",".$row["crowd_num"]."&";
      }
    }
?>
