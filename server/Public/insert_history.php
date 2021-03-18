<?php
  require "../init.php";
  $schedule_num = $_POST["schedule_num"];
  $crowd_name = $_POST["crowd_name"];
  $UID = $_POST["UID"];
  require "transfer_crowd_name.php";

  $sql1 = "SELECT `UID` FROM `Crowd_member` WHERE `crowd_num`='$crowd_num'";
  $result = mysqli_query($con, $sql1);
    while ($row = mysqli_fetch_array($result)) {
    	$P_UID = $row["UID"];
    	$sql2 = "INSERT INTO `webappdb`.`History_Train_Crowd` (`history_num`, `schedule_num`, `crowd_num`, `UID`) VALUES (NULL, '$schedule_num', '$crowd_num', '$P_UID');";
    	mysqli_query($con, $sql2);

    }
    
?>
