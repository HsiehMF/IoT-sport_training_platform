<?php
	require "../init.php";
    $schedule_num = $_POST["schedule_num"];

	$sql = "SELECT `NAME`,`User`.`UID` FROM `User` ,(SELECT `UID` FROM `History_Train_Crowd` WHERE `crowd_num`=(SELECT `crowd_num` FROM `Schedule` WHERE `schedule_num`= $schedule_num) and `schedule_num` = $schedule_num)as x WHERE x.`UID`=`User`.`UID`";
	$result = mysqli_query($con, $sql);
		while($row = mysqli_fetch_array($result)) {

			$x = $_POST["$row[NAME]"];
			  if ($x == "") 
			  	$str = $str;
			  else
				$str = $str.$x."&";
			
		}
	
	echo $str;

	if (empty($str)) {
		
	} else {

		$array = explode( "&" , $str);
	  	$j = (int)count($array) - 1;

		  	for ($i = 0 ; $i < $j ; $i++) {
		      $string = $array[$i];
		      $data = explode( "," , $string);
		      $sql = "UPDATE `Crowd_member` SET `mark` = '$data[0]' WHERE `Crowd_member`.`UID` = '$data[1]';";
		      mysqli_query($con,$sql);
		    }

	}

?>

// <script type="text/javascript">
//    window.history.go(-1);
// </script>