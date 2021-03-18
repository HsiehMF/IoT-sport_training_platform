<?php 
	require "../init.php";
	$schedule_num=$_POST["schedule_num"];
	$Estimate=$_POST["Estimate"];
	$judge=$_POST["judge"];
	$data=$_POST["data"];
		// SQL insert~!!!
	$sql = "UPDATE `Schedule` SET `Estimate`=$data WHERE `schedule_num`=$schedule_num";
	$result = mysqli_query($con,$sql);
	// mysqli_fetch_array($result);
	echo "$schedule_num<br>$data";
 ?>