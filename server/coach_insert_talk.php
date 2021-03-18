<?php
  require "init.php";
  require "count_num_talk.php";
  require "count_num_R_detail.php";

  $UID = $_POST["UID"];
  $curDay = $_POST["curDay"];
  $content = $_POST["content"];
  $crowd_name = $_POST["crowd_name"];
  require "transfer_crowd_name.php";
//  $UID=10;
//  $curDay="2016-08-13";
//  $content = 'hi';
  
  $sql1 = "INSERT INTO `webappdb`.`Talk` (`talk_num`, `talk_UID`, `talk_time`, `talk_content`) VALUES ('$talk_num', '$UID', '$curDay', '$content')";  
  $sql2 = "INSERT INTO `webappdb`.`R_Detail` (`R_detail_num`, `crowd_num`, `talk_num`) VALUES ('$R_detail_num', '$crowd_num', '$talk_num');";
  
  mysqli_query($con,$sql1);
  mysqli_query($con,$sql2);
  
?>
