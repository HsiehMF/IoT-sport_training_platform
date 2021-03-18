<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title></title>
</head>
<body>
<?php 
	require "../init.php";
	$UID=$_POST["UID"];
	$Date=$_POST["Date"];
	$item_list_num=$_POST["item_list_num"];
	// $UID=$_GET["UID"];
	// $Date=$_GET["Date"];
	// $item_list_num=$_GET["item_list_num"];


	$sql = "SELECT `line_num` FROM `Line` WHERE `schedule_num`=(SELECT `schedule_num` FROM `Schedule` WHERE (SELECT `course_num` FROM `User` where `UID`=$UID)=`course_num` and `schedule_date` like '%$Date%') order by `line_num` ASC";

	$Line_num;
	$result = mysqli_query($con,$sql);
	for ($i=0; $i < $item_list_num ; $i++) { 
		$row1 = mysqli_fetch_array($result);
		$Line_num=$row1["line_num"];
	}
	$sql1="SELECT `judgement` FROM `Line` WHERE `line_num`=$Line_num";
	// echo "$sql1";
	$result1 = mysqli_query($con,$sql1);
	$row2 = mysqli_fetch_array($result1);
	// echo "$sql1".$row2[judgement];



	// $judgement=explode(",", $row2["judgement"]);
	$judgement=$row2[judgement].","."$UID";

	$sql2="UPDATE `Line` SET `judgement`= '$judgement' WHERE `line_num`=$Line_num";
	echo "$sql2";

	// echo "$sql2";
	$result1 = mysqli_query($con,$sql2);
 ?>

</body>
</html>