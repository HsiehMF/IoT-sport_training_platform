<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
	<title></title>
</head>
<body>
<?php 
require "../init.php";

if($_SESSION['login']!="coach"){
  header('Location: index.php');
}else{

	$schedule_num= $_GET["schedule_num"];
	$schedule_date=$_GET["schedule_date"];
	$list_num=$_GET["list_num"];
	$item_name=$_GET["item_name"];
	$sql = "SELECT `UID` FROM `History_Train_Crowd` WHERE `schedule_num`=$schedule_num LIMIT 0, 30 ";
	$result = mysqli_query($con, $sql);
	if ($item_name=="跑步") {
    $item_name="running";
	}
	while($row = mysqli_fetch_array($result)) {
		echo $row[UID]."<br>";
	}

}

 ?>
</body>
</html>