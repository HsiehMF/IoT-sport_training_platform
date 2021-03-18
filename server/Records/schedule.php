<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<style>body{
      background-color:lightgreen;
      text-align:left;
                           
       }
p { 
      position:relative; 
      top:50%;  
}
 p_title{
  font-family:微軟正黑體;
  font-size:30px;
}

table, th, td {
    border: 1px solid black;
}

 

.fancytable{
border:1px solid #cccccc; width:100%;border-collapse:collapse;
}
.fancytable td{
border:1px solid #cccccc; color:#555555;text-align:center;line-height:28px;
}
.headerrow{ 
background-color:#006400;
}
.headerrow td{ 
color:#ffffff; text-align:center;
}
.datarowodd{
background-color:#ffffff;
}
.dataroweven{ 
background-color:#efefef;
}
.datarowodd td{
background-color:#ffffff;
}
.dataroweven td{ 
background-color:#efefef;
}
  </style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="http://code.highcharts.com/highcharts.js"></script>
</head>
<body>
<button id="angel">返回</button>
<script type="text/javascript">
//假如网页当中有一个id为angel的按钮
$("#angel").click(function(){
   window.history.go(-1);
});
</script>
	<?php 
		require "../init.php";
		$UID = $_GET["UID"];
		$crowd_name= $_GET["crowd_name"];
		$course_name = $_GET["course_name"];
	    $player_uid= $_GET["player_uid"];
	    $crowd_num = $_GET["crowd_num"];
	    $schedule_num= $_GET["schedule_num"];
	    $schedule_name=$_GET["schedule_name"];
	    $schedule_date=$_GET["schedule_date"];
	    $Estimate=$_GET["Estimate"];
	    session_start();
  		if($_SESSION['login']!="coach"){
  		header('Location: index.php');
  		}else{
  			$sql_1 = "SELECT `schedule_num`,`schedule_name`,`schedule_date` ,`Estimate` FROM `Schedule` WHERE `schedule_num`=$schedule_num ";
   			$result = mysqli_query($con,$sql_1);
   			$row = mysqli_fetch_array($result);
			echo "<table class='fancytable'>";
			// echo "<tr><td>社團</td>";
			echo "<tr class='headerrow'>";
			echo "<td class='headrrow'><span style=font-family:微軟正黑體; color:white; font-size:20px'>隊伍</span></td>";
			echo "<td class='headrrow'><span style=font-family:微軟正黑體; color:white; font-size:20px'>組別</span></td>";
			echo "<td class='headrrow'><span style=font-family:微軟正黑體; color:white; font-size:20px'>課表名稱</span></td>";
			echo "<td class='headrrow'><span style=font-family:微軟正黑體; color:white; font-size:20px'>日期</span></td>";
			echo "<td class='headrrow'><span style=font-family:微軟正黑體; color:white; font-size:20px'>評語</span></td>";
			echo "</tr>";
// -------------------------------------------------
			echo "<tr>";
			// echo "<tr><td><a href='http://140.127.218.198:8080/webapp/Records/coach.php?UID=$UID'>$course_name</a></td>";
			echo "<td class='datarowodd'><span style=font-family:微軟正黑體; color:gray; font-size:20px'>$course_name</span></td>";
			echo "<td class='datarowodd'><span style=font-family:微軟正黑體; color:gray; font-size:20px'>$crowd_name</span></td>";
			echo "<td class='datarowodd'><span style=font-family:微軟正黑體; color:gray; font-size:20px'>$schedule_name</span></td>";
			echo "<td class='datarowodd'><span style=font-family:微軟正黑體; color:gray; font-size:20px'>$schedule_date</span></td>";
			echo "<td class='datarowodd'><span style=font-family:微軟正黑體; color:gray; font-size:20px'>$row[Estimate]</span></td>";
			echo "<tr>";
			echo "<table>";
// ----------------------------------------------------------------------------------AJAX
			if($row[Estimate]==""){
				$judge="false";
				echo "<input class='text'>";
				echo "<input class='fix' type='button' value='插入評語'>";
			}else {
				$judge="true";
				echo "<input class='text'>";
				echo "<input class='fix' type='button' value='修改評語'>";
			}
// ----------------------------------------------------------------------------------- training item

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
			// echo "item=$item<br>";
			$item_num=explode(",", $item);
			echo "<table>";
			echo "<tr>";
			echo "<td>項次</td>";
			echo "<td>項目名稱</td>";
			echo "<td>距離</td>";
			echo "<td>次數</td>";
			echo "<td>時間</td>";
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
				echo "<td><a href='http://140.127.218.198:8080/webapp/Records/lookdata.php?player_uid=$player_uid&schedule_date=$schedule_date&list_num=$j&item_name=$row3[item_name]&item_time=$row3[item_time]'>查看資料</a></td>";
				echo "</tr>";
				$j++;
			}
			echo "</table>";
  		}

 	?>
 <!--  insert the Estimate-->
<script type="text/javascript">
var value;
$(document).ready(function(){
	$(".fix").click(function(){
   		value="\""+$(".text").val()+"\"";
   		$.ajax({url:"updateschudule.php", type:"POST",data:{schedule_num:"<?php echo "$schedule_num"; ?>",Estimate: "<?php   echo "$Estimate"; ?>",judge:" <?php echo "$judge"; ?>",data:value},error: function(xhr) {alert('Ajax request 發生錯誤');},success: function(result){	alert(result+"插入成功!!!") ;} 
   		 })
//   		location.reload();
	});
	
});
</script>
</body>
</html>
