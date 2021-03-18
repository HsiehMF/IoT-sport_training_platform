<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" href="student_schedule.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://code.highcharts.com/highcharts.js"></script>
  <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
</head>
<body>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<?php 
	require "../init.php";
	$player_uid = $_GET["player_uid"];
	$schedule_num= $_GET["schedule_num"];
	$schedule_name=$_GET["schedule_name"];
	$schedule_date=$_GET["schedule_date"];
	$player_name=$_GET["player_name"];

	echo '<div class="collection">';
      echo "<a class='collection-item active'>$schedule_name \t -$player_name</a>";

	$sql = "SELECT `line_num` FROM `Line` WHERE `schedule_num`=$schedule_num";
  			 // echo "sql = $sql";
  	$result = mysqli_query($con,$sql);	
  	$line="";
  	while($row1 = mysqli_fetch_array($result)) {
  	$line="$line,$row1[line_num]";
  				// echo "$row1[line_num]";
	}
			// echo "$line";
	$Line_num=explode(",",$line);
	$i=0;
	$item="";
	while ($i <= count($Line_num)) {
		$sql1 = "SELECT `item_id` FROM `Line` where `line_num` =$Line_num[$i]";
				// echo "$sql1<br>";
		$i++;
		$result1 = mysqli_query($con,$sql1);
		$row2 = mysqli_fetch_array($result1);
		$item="$item,$row2[item_id]";
	}
		$item_num=explode(",", $item);
		echo "<table class='highlight'>";
		echo "<tr>";
		echo "<th>項次</th>";
		echo "<th>名稱</th>";
		echo "<th>距離</th>";
		echo "<th>次數</th>";
		echo "<th>時間</th>";
		echo "<th>數據</th>";
		echo "</tr>";
		$j=1;
		for($i=2;$i<count($item_num)-1;$i++){
				
			$sql2 = "SELECT `item_name` ,`item_sub_name`,`item_times`,`item_time`FROM `Item` where `item_id`=$item_num[$i]";
			$result1 = mysqli_query($con,$sql2);
			$row3 = mysqli_fetch_array($result1);
			echo "<tr>";
			echo "<td>$j</td>";
			echo "<td>$row3[item_name]</td>";
			echo "<td>$row3[item_sub_name]</td>";
			echo "<td>$row3[item_times]</td>";
			echo "<td>$row3[item_time]</td>";
			echo "<td><a class='waves-effect waves-light btn' href='http://140.127.218.198:8080/webapp/Records/lookdata.php?player_uid=$player_uid&schedule_date=$schedule_date&list_num=$j&item_name=$row3[item_name]&item_time=$row3[item_time]'>點擊查看</a></td>";
			echo "</tr>";
			$j++;
		}
		echo "</table>";
	echo '</div>';

?>
</body>
</html>