<?php
require "../init.php";


$UID=$_POST["UID"];
$curDay=$_POST["curDay"];
$grades=$_POST["grades"];

// $UID=$_GET["UID"];
// $curDay=$_GET["curDay"];
// $grades=$_GET["grades"];

// $schedule_num="";

//$UID=10;
//$curDay="2016-08-27";
//$grades="5,5,5";
// SELECT `schedule_num` FROM `Schedule` WHERE (SELECT `course_num` FROM `User` WHERE `UID`=10)=`course_num` and `schedule_date` like "%2016-11-02%"

$sql = "SELECT `schedule_num` FROM `Schedule` WHERE (SELECT `course_num` FROM `User` WHERE `UID`=$UID)=`course_num` and `schedule_date` like \"%$curDay%\"";
echo "sql=".$sql."<br>";

$result = mysqli_query($con, $sql);
$flag=-1;
while ($row = mysqli_fetch_array($result)) {

	$sql1 = "SELECT count(*) as x FROM `Self_evaluation` WHERE `schedule_num`= $row[schedule_num] and `Date` like \"%$curDay%\"";
	$result1 = mysqli_query($con, $sql1);
	echo "sql1=".$sql1."<br>";
	
	while ($row1 = mysqli_fetch_array($result1)){
		// $i=$row1[x];
		if ($row1[x]!="0") {
			$flag=$row1[x];
		}
		
	}
	echo "flag\t"."$flag<br>";
	
	if ($flag>-1){
		$sql2="UPDATE `Self_evaluation` SET `grades`=\" $grades \" WHERE `schedule_num`= $row[schedule_num] and `UID`= $UID and `Date` like \"%$curDay%\"";
		echo "sql2=".$sql2."<br>";
		$result1 = mysqli_query($con, $sql2);
		$flag--;


		// $sql2="INSERT INTO `Self_evaluation`(`UID`, `schedule_num`, `Date`, `grades`) VALUES ($UID,$row[schedule_num],$curDay, \"  $grades  \" )";
		// $result1 = mysqli_query($con, $sql2);
		// echo "sql2=".$sql2."<br>";
	}else{

		$sql2="INSERT INTO `Self_evaluation`(`UID`, `schedule_num`, `Date`, `grades`) VALUES ($UID,$row[schedule_num],\"$curDay\", \"  $grades  \" )";
		$result1 = mysqli_query($con, $sql2);
		echo "sql2=".$sql2."<br>";


		// $sql2="UPDATE `Self_evaluation` SET `grades`=\" $grades \" WHERE `schedule_num`= $row[schedule_num] and `UID`= $UID and `Date` like \"%$curDay%\"";
		// echo "sql2=".$sql2."<br>";
		// $result1 = mysqli_query($con, $sql2);
	}
 

 }
?>

