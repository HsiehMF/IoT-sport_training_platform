<?php
require "init.php";


$UID=$_POST["UID"];
$curDay=$_POST["curDay"];
$grades=$_POST["grades"];
$schedule_num="";

//$UID=10;
//$curDay="2016-08-27";
//$grades="5,5,5";


$sql = "SELECT Schedule.schedule_num FROM Schedule,(SELECT group_num FROM `Detail` WHERE UID='$UID') as x where Schedule.group_num = x.group_num and schedule_date like '%$curDay%' ";
$result = mysqli_query($con, $sql);

while ($row = mysqli_fetch_array($result)) {
	$schedule_num=$row["schedule_num"];
//	echo "eo4".$schedule_num."<br>";
  }

$sql2="INSERT INTO webappdb.Self_evaluation (Se_num, UID, schedule_num, grades) VALUES ('', '$UID', '$schedule_num', '$grades')";
mysqli_query($con,$sql2);
//echo "eo4".$schedule_num."<br>";
//echo $sql2;
?>

