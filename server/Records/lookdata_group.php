<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
	<title></title>
</head>
<body>
<div id="container_right"></div>
<div id="container_left"></div>
<div id="container_heart"></div>
<?php 
require "../init.php";
session_start();
if($_SESSION['login']!="coach"){
  header('Location: index.php');
}else{
	
	$schedule_num= $_GET["schedule_num"];
	$schedule_date=$_GET["schedule_date"];
	$list_num=$_GET["list_num"];
	$item_name=$_GET["item_name"];
	$sql = "SELECT `UID` FROM `History_Train_Crowd` WHERE `schedule_num`=$schedule_num LIMIT 0, 30 ";
	$result = mysqli_query($con, $sql);
    $UID="-1";
	if ($item_name=="跑步") {
    $item_name="running";
	}
	$filename_left;
	$filename_right;
	$filename_heartrate;
	$i=0;
	$schedule_date=explode(" ",$schedule_date);
	while($row = mysqli_fetch_array($result)) {
		// echo "i=$i<br>";
		// echo $row[UID]."<br>";
        $UID=$UID.",".$row[UID];
        echo $UID."<br>";
		$filename_left[$i]=$row[UID]."_$schedule_date[0]_"."$list_num"."_$item_name"."_1.txt";
		// echo $filename_left[$i]."<br>";
		$filename_right[$i]=$row[UID]."_$schedule_date[0]_"."$list_num"."_$item_name"."_0.txt";
		// echo $filename_right[$i]."<br>";
		$filename_heartrate[$i]=$row[UID]."_$schedule_date[0]_"."$list_num"."_$item_name"."_heartrate.txt";
		// echo $filename_heartrate[$i]."<br>";
		$i++;
	}
	$j=0;
	$k=0;
	$l=0;
	$temp_j;
	$temp_k;
	$temp_l;

	$data_ax1="[";$data_gy1="[";
	$data_ax2="[";$data_gy2="[";
	$data_ax3="[";$data_gy3="[";
	$data_ax4="[";$data_gy4="[";
	$data_ax5="[";$data_gy5="[";
	$data_ax6="[";$data_gy6="[";
	$data_ax7="[";$data_gy7="[";
	$data_ax8="[";$data_gy8="[";
	$data_ax9="[";$data_gy9="[";
	$data_ax10="[";$data_gy10="[";
	$data_ax11="[";$data_gy11="[";
	$data_ax12="[";$data_gy12="[";
	$data_ax13="[";$data_gy13="[";
	$data_ax14="[";$data_gy14="[";
	$data_ax15="[";$data_gy15="[";
	$data_ax16="[";$data_gy16="[";
	$data_ax17="[";$data_gy17="[";
	$data_ax18="[";$data_gy18="[";
	$data_ax19="[";$data_gy19="[";
	$data_ax20="[";$data_gy20="[";
	$data_ax21="[";$data_gy21="[";
	$data_ax22="[";$data_gy22="[";
	$data_ax23="[";$data_gy23="[";
	$data_ax24="[";$data_gy24="[";
	$data_ax25="[";$data_gy25="[";
	$data_ax26="[";$data_gy26="[";
	$data_ax27="[";$data_gy27="[";
	$data_ax28="[";$data_gy28="[";
	$data_ax29="[";$data_gy29="[";
	$data_ax30="[";$data_gy30="[";

	$data_ax_1="[";$data_gy_1="[";
	$data_ax_2="[";$data_gy_2="[";
	$data_ax_3="[";$data_gy_3="[";
	$data_ax_4="[";$data_gy_4="[";
	$data_ax_5="[";$data_gy_5="[";
	$data_ax_6="[";$data_gy_6="[";
	$data_ax_7="[";$data_gy_7="[";
	$data_ax_8="[";$data_gy_8="[";
	$data_ax_9="[";$data_gy_9="[";
	$data_ax_10="[";$data_gy_10="[";
	$data_ax_11="[";$data_gy_11="[";
	$data_ax_12="[";$data_gy_12="[";
	$data_ax_13="[";$data_gy_13="[";
	$data_ax_14="[";$data_gy_14="[";
	$data_ax_15="[";$data_gy_15="[";
	$data_ax_16="[";$data_gy_16="[";
	$data_ax_17="[";$data_gy_17="[";
	$data_ax_18="[";$data_gy_18="[";
	$data_ax_19="[";$data_gy_19="[";
	$data_ax_20="[";$data_gy_20="[";
	$data_ax_21="[";$data_gy_21="[";
	$data_ax_22="[";$data_gy_22="[";
	$data_ax_23="[";$data_gy_23="[";
	$data_ax_24="[";$data_gy_24="[";
	$data_ax_25="[";$data_gy_25="[";
	$data_ax_26="[";$data_gy_26="[";
	$data_ax_27="[";$data_gy_27="[";
	$data_ax_28="[";$data_gy_28="[";
	$data_ax_29="[";$data_gy_29="[";
	$data_ax_30="[";$data_gy_30="[";


    $data_heartrate1="[";
    $data_heartrate2="[";
    $data_heartrate3="[";
    $data_heartrate4="[";
    $data_heartrate5="[";
    $data_heartrate6="[";
    $data_heartrate7="[";
    $data_heartrate8="[";
    $data_heartrate9="[";
    $data_heartrate10="[";
    $data_heartrate11="[";
    $data_heartrate12="[";
    $data_heartrate13="[";
    $data_heartrate14="[";
    $data_heartrate15="[";
    $data_heartrate16="[";
    $data_heartrate17="[";
    $data_heartrate18="[";
    $data_heartrate19="[";
    $data_heartrate20="[";
    $data_heartrate21="[";
    $data_heartrate22="[";
    $data_heartrate23="[";
    $data_heartrate24="[";
    $data_heartrate25="[";
    $data_heartrate26="[";
    $data_heartrate27="[";
    $data_heartrate28="[";
    $data_heartrate29="[";
    $data_heartrate30="[";

	while ( $i>=0) {
		$total_j=0;
		$total_k=0;
		$total_l=0;
		// echo "../uploads/upload/$filename_left[$i]"."<br>";
		$fp1=fopen("../uploads/upload/$filename_left[$i]","r");
		while ($mydata=fgets($fp1,60)) {
			if ($i==29) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax30="$data_ax30"."$line_data[1],";
    			$data_gy30="$data_gy30"."$line_data[3],";
				
			}
			if ($i==28) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax29="$data_ax29"."$line_data[1],";
    			$data_gy29="$data_gy29"."$line_data[3],";
				
			}
			if ($i==27) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax28="$data_ax28"."$line_data[1],";
    			$data_gy28="$data_gy28"."$line_data[3],";
			}
			if ($i==26) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax27="$data_ax27"."$line_data[1],";
    			$data_gy27="$data_gy27"."$line_data[3],";
				
			}
			if ($i==25) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax26="$data_ax26"."$line_data[1],";
    			$data_gy26="$data_gy26"."$line_data[3],";
				
			}
			if ($i==24) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax25="$data_ax25"."$line_data[1],";
    			$data_gy25="$data_gy25"."$line_data[3],";

			}
			if ($i==23) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax24="$data_ax24"."$line_data[1],";
    			$data_gy24="$data_gy24"."$line_data[3],";
			}
			if ($i==22) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax23="$data_ax23"."$line_data[1],";
    			$data_gy23="$data_gy23"."$line_data[3],";
			}
			if ($i==21) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax22="$data_ax22"."$line_data[1],";
    			$data_gy22="$data_gy22"."$line_data[3],";

			}
			if ($i==20) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax21="$data_ax21"."$line_data[1],";
    			$data_gy21="$data_gy21"."$line_data[3],";



			}
			if ($i==19) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax20="$data_ax20"."$line_data[1],";
    			$data_gy20="$data_gy20"."$line_data[3],";
			}			
			if ($i==18) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax19="$data_ax19"."$line_data[1],";
    			$data_gy19="$data_gy19"."$line_data[3],";
			}
			if ($i==17) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax18="$data_ax18"."$line_data[1],";
    			$data_gy18="$data_gy18"."$line_data[3],";

			}
			if ($i==16) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax17="$data_ax17"."$line_data[1],";
    			$data_gy17="$data_gy17"."$line_data[3],";

			}
			if ($i==15) {
			
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax16="$data_ax16"."$line_data[1],";
    			$data_gy16="$data_gy16"."$line_data[3],";

			}
			if ($i==14) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax15="$data_ax15"."$line_data[1],";
    			$data_gy15="$data_gy15"."$line_data[3],";


			}
			if ($i==13) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax14="$data_ax14"."$line_data[1],";
    			$data_gy14="$data_gy14"."$line_data[3],";
			}
			if ($i==12) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax13="$data_ax13"."$line_data[1],";
    			$data_gy13="$data_gy13"."$line_data[3],";


			}
			if ($i==11) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax12="$data_ax12"."$line_data[1],";
    			$data_gy12="$data_gy12"."$line_data[3],";



			}
			if ($i==10) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax11="$data_ax11"."$line_data[1],";
    			$data_gy11="$data_gy11"."$line_data[3],";



			}
			if ($i==9) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax10="$data_ax10"."$line_data[1],";
    			$data_gy10="$data_gy10"."$line_data[3],";

			}
			if ($i==8) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax9="$data_ax9"."$line_data[1],";
    			$data_gy9="$data_gy9"."$line_data[3],";
			}			
			if ($i==7) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax8="$data_ax8"."$line_data[1],";
    			$data_gy8="$data_gy8"."$line_data[3],";
			}
			if ($i==6) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax7="$data_ax7"."$line_data[1],";
    			$data_gy7="$data_gy7"."$line_data[3],";
				
			}
			if ($i==5) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax6="$data_ax6"."$line_data[1],";
    			$data_gy6="$data_gy6"."$line_data[3],";
			}
			if ($i==4) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax5="$data_ax5"."$line_data[1],";
    			$data_gy5="$data_gy5"."$line_data[3],";

			}
			if ($i==3) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax4="$data_ax4"."$line_data[1],";
    			$data_gy4="$data_gy4"."$line_data[3],";
			}
			if ($i==2) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax3="$data_ax3"."$line_data[1],";
    			$data_gy3="$data_gy3"."$line_data[3],";

			}		
			if ($i==1) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax2="$data_ax2"."$line_data[1],";
    			$data_gy2="$data_gy2"."$line_data[3],";

				
			}
			if ($i==0) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax1="$data_ax1"."$line_data[1],";
    			$data_gy1="$data_gy1"."$line_data[3],";

			}	
			$total_j++;
		}
		fclose($fp1);
		// echo "../uploads/upload/$filename_right[$i]"."<br>";
		$fp2=fopen("../uploads/upload/$filename_right[$i]","r");
		while ($mydata=fgets($fp2,60)) {
			if ($i==29) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_30="$data_ax_30"."$line_data[1],";
    			$data_gy_30="$data_gy_30"."$line_data[3],";
				
			}
			if ($i==28) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_29="$data_ax_29"."$line_data[1],";
    			$data_gy_29="$data_gy_29"."$line_data[3],";
				
			}
			if ($i==27) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_28="$data_ax_28"."$line_data[1],";
    			$data_gy_28="$data_gy_28"."$line_data[3],";
			}
			if ($i==26) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_27="$data_ax_27"."$line_data[1],";
    			$data_gy_27="$data_gy_27"."$line_data[3],";
				
			}
			if ($i==25) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_26="$data_ax_26"."$line_data[1],";
    			$data_gy_26="$data_gy_26"."$line_data[3],";
				
			}
			if ($i==24) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_25="$data_ax_25"."$line_data[1],";
    			$data_gy_25="$data_gy_25"."$line_data[3],";

			}
			if ($i==23) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_24="$data_ax_24"."$line_data[1],";
    			$data_gy_24="$data_gy_24"."$line_data[3],";
			}
			if ($i==22) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_23="$data_ax_23"."$line_data[1],";
    			$data_gy_23="$data_gy_23"."$line_data[3],";
			}
			if ($i==21) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_22="$data_ax_22"."$line_data[1],";
    			$data_gy_22="$data_gy_22"."$line_data[3],";

			}
			if ($i==20) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_21="$data_ax_21"."$line_data[1],";
    			$data_gy_21="$data_gy_21"."$line_data[3],";



			}
			if ($i==19) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_20="$data_ax_20"."$line_data[1],";
    			$data_gy_20="$data_gy_20"."$line_data[3],";
			}			
			if ($i==18) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_19="$data_ax_19"."$line_data[1],";
    			$data_gy_19="$data_gy_19"."$line_data[3],";
			}
			if ($i==17) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_18="$data_ax_18"."$line_data[1],";
    			$data_gy_18="$data_gy_18"."$line_data[3],";

			}
			if ($i==16) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_17="$data_ax_17"."$line_data[1],";
    			$data_gy_17="$data_gy_17"."$line_data[3],";

			}
			if ($i==15) {
			
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_16="$data_ax_16"."$line_data[1],";
    			$data_gy_16="$data_gy_16"."$line_data[3],";

			}
			if ($i==14) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_15="$data_ax_15"."$line_data[1],";
    			$data_gy_15="$data_gy_15"."$line_data[3],";


			}
			if ($i==13) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_14="$data_ax_14"."$line_data[1],";
    			$data_gy_14="$data_gy_14"."$line_data[3],";
			}
			if ($i==12) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_13="$data_ax_13"."$line_data[1],";
    			$data_gy_13="$data_gy_13"."$line_data[3],";


			}
			if ($i==11) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_12="$data_ax_12"."$line_data[1],";
    			$data_gy_12="$data_gy_12"."$line_data[3],";



			}
			if ($i==10) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_11="$data_ax_11"."$line_data[1],";
    			$data_gy_11="$data_gy_11"."$line_data[3],";



			}
			if ($i==9) {
				
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_10="$data_ax_10"."$line_data[1],";
    			$data_gy_10="$data_gy_10"."$line_data[3],";

			}
			if ($i==8) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_9="$data_ax_9"."$line_data[1],";
    			$data_gy_9="$data_gy_9"."$line_data[3],";
			}			
			if ($i==7) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_8="$data_ax_8"."$line_data[1],";
    			$data_gy_8="$data_gy_8"."$line_data[3],";
			}
			if ($i==6) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_7="$data_ax_7"."$line_data[1],";
    			$data_gy_7="$data_gy_7"."$line_data[3],";
				
			}
			if ($i==5) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_6="$data_ax_6"."$line_data[1],";
    			$data_gy_6="$data_gy_6"."$line_data[3],";
			}
			if ($i==4) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_5="$data_ax_5"."$line_data[1],";
    			$data_gy_5="$data_gy_5"."$line_data[3],";

			}
			if ($i==3) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_4="$data_ax_4"."$line_data[1],";
    			$data_gy_4="$data_gy_4"."$line_data[3],";
			}
			if ($i==2) {
				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_3="$data_ax_3"."$line_data[1],";
    			$data_gy_3="$data_gy_3"."$line_data[3],";

			}		
			if ($i==1) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_2="$data_ax_2"."$line_data[1],";
    			$data_gy_2="$data_gy_2"."$line_data[3],";

				
			}
			if ($i==0) {

				$line_data=explode(",",$mydata);
				if(-40000>$line_data[1]||$line_data[1]>40000 ){
        			continue;
    			}
    			if(-40000>$line_data[3]||$line_data[3]>40000 ){
        			continue;
    			}

    			if ($line_data[1]== "-" || $line_data[3]== "-") {
        			continue;
    			}

    			if ($line_data[1]== 0 || $line_data[3]== 0) {
        			continue;
   				 }
  		  		if (!isset($line_data[1])||!isset($line_data[3])) {
    				continue;
    			}
    			$data_ax_1="$data_ax_1"."$line_data[1],";
    			$data_gy_1="$data_gy_1"."$line_data[3],";

			}	


			$total_k++;
		}
		fclose($fp2);
		// echo "../uploads/upload/$filename_heartrate[$i]"."<br>";
		$fp3=fopen("../uploads/upload/$filename_heartrate[$i]","r");
		while ($mydata=fgets($fp3,60)) {

			if ($i==29) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate30="$data_heartrate30"."$data_heart,";
			}
			if ($i==28) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate29="$data_heartrate29"."$data_heart,";
			}
			if ($i==27) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate28="$data_heartrate28"."$data_heart,";
			}
			if ($i==26) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate27="$data_heartrate27"."$data_heart,";
			}
			if ($i==25) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate26="$data_heartrate26"."$data_heart,";
			}
			if ($i==24) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate25="$data_heartrate25"."$data_heart,";
			}
			if ($i==23) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate24="$data_heartrate24"."$data_heart,";
			}
			if ($i==22) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate23="$data_heartrate23"."$data_heart,";
			}
			if ($i==21) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate22="$data_heartrate22"."$data_heart,";
			}
			if ($i==20) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate21="$data_heartrate21"."$data_heart,";
			}
			if ($i==19) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate20="$data_heartrate20"."$data_heart,";
			}			
			if ($i==18) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate19="$data_heartrate19"."$data_heart,";
			}
			if ($i==17) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate18="$data_heartrate18"."$data_heart,";
			}
			if ($i==16) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate17="$data_heartrate17"."$data_heart,"; 
			}
			if ($i==15) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate16="$data_heartrate16"."$data_heart,"; 
			}
			if ($i==14) {
				
                $data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate15="$data_heartrate15"."$data_heart,"; 
			}
			if ($i==13) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate14="$data_heartrate14"."$data_heart,"; 
			}
			if ($i==12) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate13="$data_heartrate13"."$data_heart,"; 
			}
			if ($i==11) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate12="$data_heartrate12"."$data_heart,"; 
			}
			if ($i==10) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate11="$data_heartrate11"."$data_heart,"; 
			}
			if ($i==9) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate10="$data_heartrate10"."$data_heart,";  
			}
			if ($i==8) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate9="$data_heartrate9"."$data_heart,";  

			}			
			if ($i==7) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate8="$data_heartrate8"."$data_heart,";      
			}
			if ($i==6) {
                $data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate7="$data_heartrate7"."$data_heart,";      
				
			}
			if ($i==5) {
                $data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate6="$data_heartrate6"."$data_heart,";                          
				
			}
			if ($i==4) {
                $data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate5="$data_heartrate5"."$data_heart,";                
				
			}
			if ($i==3) {
			    
                $data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate4="$data_heartrate4"."$data_heart,";
			}
			if ($i==2) {
				$data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate3="$data_heartrate3"."$data_heart,";
                
			}		
			if ($i==1) {
                $data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate2="$data_heartrate2"."$data_heart,";
				
			}
			if ($i==0) {
		      
                $data_heart=$mydata;
                $data_heart=(int)$data_heart;
                if ($data_heart==0) {
                    continue;
                }
                if ($data_heart>220) {
                    continue;
                }
                $data_heartrate1="$data_heartrate1"."$data_heart,";
			}	

			$total_l++;
            // echo "total_j=   $total_j   ";
            // echo "total_k=   $total_k   ";
            // echo "total_l=   $total_l   <br>";
		}
		fclose($fp3);
		$temp_j="run".$temp_j.",".$total_j;
		$temp_k="runR".$temp_k.",".$total_k;
		$temp_l="heartrates".$temp_l.",".$total_l;
		$i--;
	}
	// echo $temp_j."\t".$temp_k."\t".$temp_l."<br>";//計數器

	$data_ax1="$data_ax1"."]";$data_gy1="$data_gy1"."]";
	$data_ax2="$data_ax2"."]";$data_gy2="$data_gy2"."]";
	$data_ax3="$data_ax3"."]";$data_gy3="$data_gy3"."]";
	$data_ax4="$data_ax4"."]";$data_gy4="$data_gy4"."]";
	$data_ax5="$data_ax5"."]";$data_gy5="$data_gy5"."]";
	$data_ax6="$data_ax6"."]";$data_gy6="$data_gy6"."]";
	$data_ax7="$data_ax7"."]";$data_gy7="$data_gy7"."]";
	$data_ax8="$data_ax8"."]";$data_gy8="$data_gy8"."]";
	$data_ax9="$data_ax9"."]";$data_gy9="$data_gy9"."]";
	$data_ax10="$data_ax10"."]";$data_gy10="$data_gy10"."]";

	$data_ax11="$data_ax11"."]";$data_gy11="$data_gy11"."]";
	$data_ax12="$data_ax12"."]";$data_gy12="$data_gy12"."]";
	$data_ax13="$data_ax13"."]";$data_gy13="$data_gy13"."]";
	$data_ax14="$data_ax14"."]";$data_gy14="$data_gy14"."]";
    $data_ax15="$data_ax15"."]";$data_gy15="$data_gy15"."]";
	$data_ax16="$data_ax16"."]";$data_gy16="$data_gy16"."]";
	$data_ax17="$data_ax17"."]";$data_gy17="$data_gy17"."]";
	$data_ax18="$data_ax18"."]";$data_gy18="$data_gy18"."]";
	$data_ax19="$data_ax19"."]";$data_gy19="$data_gy19"."]";
    $data_ax20="$data_ax20"."]";$data_gy20="$data_gy20"."]";
    
    $data_ax21="$data_ax21"."]";$data_gy21="$data_gy21"."]";
	$data_ax22="$data_ax22"."]";$data_gy22="$data_gy22"."]";
	$data_ax23="$data_ax23"."]";$data_gy23="$data_gy23"."]";
	$data_ax24="$data_ax24"."]";$data_gy24="$data_gy24"."]";
	$data_ax25="$data_ax25"."]";$data_gy25="$data_gy25"."]";
	$data_ax26="$data_ax26"."]";$data_gy26="$data_gy26"."]";
	$data_ax27="$data_ax27"."]";$data_gy27="$data_gy27"."]";
	$data_ax28="$data_ax28"."]";$data_gy28="$data_gy28"."]";
	$data_ax29="$data_ax29"."]";$data_gy29="$data_gy29"."]";
	$data_ax30="$data_ax30"."]";$data_gy30="$data_gy30"."]";

// -------------------------------------------------------------------------
    $data_ax_1="$data_ax_1"."]";$data_gy_1="$data_gy_1"."]";
    $data_ax_2="$data_ax_2"."]";$data_gy_2="$data_gy_2"."]";
    $data_ax_3="$data_ax_3"."]";$data_gy_3="$data_gy_3"."]";
    $data_ax_4="$data_ax_4"."]";$data_gy_4="$data_gy_4"."]";
    $data_ax_5="$data_ax_5"."]";$data_gy_5="$data_gy_5"."]";
    $data_ax_6="$data_ax_6"."]";$data_gy_6="$data_gy_6"."]";
    $data_ax_7="$data_ax_7"."]";$data_gy_7="$data_gy_7"."]";
    $data_ax_8="$data_ax_8"."]";$data_gy_8="$data_gy_8"."]";
    $data_ax_9="$data_ax_9"."]";$data_gy_9="$data_gy_9"."]";
    $data_ax_10="$data_ax_10"."]";$data_gy_10="$data_gy_10"."]";

    $data_ax_11="$data_ax_11"."]";$data_gy_11="$data_gy_11"."]";
    $data_ax_12="$data_ax_12"."]";$data_gy_12="$data_gy_12"."]";
    $data_ax_13="$data_ax_13"."]";$data_gy_13="$data_gy_13"."]";
    $data_ax_14="$data_ax_14"."]";$data_gy_14="$data_gy_14"."]";
    $data_ax_15="$data_ax_15"."]";$data_gy_15="$data_gy_15"."]";
    $data_ax_16="$data_ax_16"."]";$data_gy_16="$data_gy_16"."]";
    $data_ax_17="$data_ax_17"."]";$data_gy_17="$data_gy_17"."]";
    $data_ax_18="$data_ax_18"."]";$data_gy_18="$data_gy_18"."]";
    $data_ax_19="$data_ax_19"."]";$data_gy_19="$data_gy_19"."]";
    $data_ax_20="$data_ax_20"."]";$data_gy_20="$data_gy_20"."]";

    $data_ax_21="$data_ax_21"."]";$data_gy_21="$data_gy_21"."]";
    $data_ax_22="$data_ax_22"."]";$data_gy_22="$data_gy_22"."]";
    $data_ax_23="$data_ax_23"."]";$data_gy_23="$data_gy_23"."]";
    $data_ax_24="$data_ax_24"."]";$data_gy_24="$data_gy_24"."]";
    $data_ax_25="$data_ax_25"."]";$data_gy_25="$data_gy_25"."]";
    $data_ax_26="$data_ax_26"."]";$data_gy_26="$data_gy_26"."]";
    $data_ax_27="$data_ax_27"."]";$data_gy_27="$data_gy_27"."]";
    $data_ax_28="$data_ax_28"."]";$data_gy_28="$data_gy_28"."]";
    $data_ax_29="$data_ax_29"."]";$data_gy_29="$data_gy_29"."]";
    $data_ax_30="$data_ax_30"."]";$data_gy_30="$data_gy_30"."]";


    $data_heartrate1=$data_heartrate1."]";
    $data_heartrate2=$data_heartrate2."]";
    $data_heartrate3=$data_heartrate3."]";
    $data_heartrate4=$data_heartrate4."]";
    $data_heartrate5=$data_heartrate5."]";
    $data_heartrate6=$data_heartrate6."]";
    $data_heartrate7=$data_heartrate7."]";
    $data_heartrate8=$data_heartrate8."]";
    $data_heartrate9=$data_heartrate9."]";
    $data_heartrate10=$data_heartrate10."]";
    $data_heartrate11=$data_heartrate11."]";
    $data_heartrate12=$data_heartrate12."]";
    $data_heartrate13=$data_heartrate13."]";
    $data_heartrate14=$data_heartrate14."]";
    $data_heartrate15=$data_heartrate15."]";
    $data_heartrate16=$data_heartrate16."]";
    $data_heartrate17=$data_heartrate17."]";
    $data_heartrate18=$data_heartrate18."]";
    $data_heartrate19=$data_heartrate19."]";
    $data_heartrate20=$data_heartrate20."]";
    $data_heartrate21=$data_heartrate21."]";
    $data_heartrate22=$data_heartrate22."]";
    $data_heartrate23=$data_heartrate23."]";
    $data_heartrate24=$data_heartrate24."]";
    $data_heartrate25=$data_heartrate25."]";
    $data_heartrate26=$data_heartrate26."]";
    $data_heartrate27=$data_heartrate27."]";
    $data_heartrate28=$data_heartrate28."]";
    $data_heartrate29=$data_heartrate29."]";
    $data_heartrate30=$data_heartrate30."]";


	// echo "$data_ax30 \n $data_ax30 \n $data_gy29 \n $data_ax29 \n $data_gy28 \n $data_ax28 \n $data_gy27 \n $data_ax27 \n $data_ax26 \n $data_ax26\n $data_ax25 \n $data_ax25 \n $data_gy24 \n $data_ax24 \n $data_gy23 \n $data_ax23 \n $data_gy22 \n $data_ax22 \n $data_ax21 \n $data_ax21 \n";
	// echo "$data_ax11 \n $data_ax11 \n $data_gy19 \n $data_ax19 \n $data_gy18 \n $data_ax18 \n $data_gy17 \n $data_ax17 \n $data_ax16 \n $data_ax16\n $data_ax15 \n $data_ax15 \n $data_gy14 \n $data_ax14 \n $data_gy13 \n $data_ax13 \n $data_gy12 \n $data_ax12 \n $data_ax11 \n $data_ax11 \n";
	// echo "$data_ax10 \n $data_ax10 \n $data_gy9 \n $data_ax9 \n $data_gy8 \n $data_ax8 \n $data_gy7 \n $data_ax7 \n $data_ax6 \n $data_ax6\n $data_ax5 \n $data_ax5 \n $data_gy4 \n $data_ax4 \n $data_gy3 \n $data_ax3 \n $data_gy2 \n $data_ax2 \n $data_ax1 \n $data_ax1 ";

	// echo "start!!!<br>$data_gy_30 \n $data_ax_30 \n $data_gy_29 \n $data_ax_29 \n $data_gy_28 \n $data_ax_28 \n $data_gy_27 \n $data_ax_27 \n $data_ax_26 \n $data_ax_26\n $data_ax_25 \n $data_ax_25 \n $data_gy_24 \n $data_ax_24 \n $data_gy_23 \n $data_ax_23 \n $data_gy_22 \n $data_ax_22 \n $data_ax_21 \n $data_ax_21 \n";
	// echo "start!!!<br>$data_gy_11 \n $data_ax_11 \n $data_gy_19 \n $data_ax_19 \n $data_gy_18 \n $data_ax_18 \n $data_gy_17 \n $data_ax_17 \n $data_ax_16 \n $data_ax_16\n $data_ax_15 \n $data_ax_15 \n $data_gy_14 \n $data_ax_14 \n $data_gy_13 \n $data_ax_13 \n $data_gy_12 \n $data_ax_12 \n $data_ax_11 \n $data_ax_11 \n";
	// echo "$data_gy_10 \n $data_ax_10 \n $data_gy_9 \n $data_ax_9 \n $data_gy_8 \n $data_ax_8 \n $data_gy_7 \n $data_ax_7 \n $data_ax_6 \n $data_ax_6\n $data_ax_5 \n $data_ax_5 \n $data_gy_4 \n $data_ax_4 \n $data_gy_3 \n $data_ax_3 \n $data_gy_2 \n $data_ax_2 \n $data_ax_1 \n $data_ax_1 <br><br>";


    // echo "$data_heartrate1    $data_heartrate2  $data_heartrate3    $data_heartrate4   $data_heartrate5    $data_heartrate6   $data_heartrate7    $data_heartrate8  $data_heartrate9    $data_heartrate10  $data_heartrate11    $data_heartrate12  $data_heartrate13    $data_heartrate14   $data_heartrate15    $data_heartrate16   $data_heartrate17    $data_heartrate18  $data_heartrate19    $data_heartrate20  $data_heartrate21    $data_heartrate22  $data_heartrate23    $data_heartrate24   $data_heartrate25    $data_heartrate26   $data_heartrate27    $data_heartrate28  $data_heartrate29    $data_heartrate30  ";
}
function findmax($num) {
    $temp=explode(",", $num);
    $max=0;
    $i=0;
    while (count($temp)>$i ) {
        if ($max<$temp[$i]) {
            $max=$temp[$i];
        }
        $i++;
    }
    return $max;
}
function categories($categories) {
    
    $categorieF="[";
    for ($i=0; $i <(int)$categories ; $i++) { 
      $categorieF="$categorieF"."$i,";
    }
    $categorieF="$categorieF"."]";
    
    return $categorieF;
}

$categoriesLR=findmax($temp_j);
$categoriesRR=findmax($temp_k);
$categoriesHH=findmax($temp_l);

$categoriesLR=categories($categoriesLR);
$categoriesRR=categories($categoriesRR);
$categoriesHH=categories($categoriesHH);

echo "$categoriesLR <br> $categoriesRR <br> $categoriesHH";

$sql1 = "SELECT `NAME` FROM `User` WHERE `UID`=10 LIMIT 0, 30 ";
$result1 = mysqli_query($con, $sql1);
$name="empty";
while($row1 = mysqli_fetch_array($result1)) {
    $name="$name,".$row1[NAME];
}
echo $name;
 ?>
</body>
<script type="text/javascript">
    $(function () {
    $('#container_right').highcharts({
        title: {
            text: '右腳變化量',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            // categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May']
            categories: <?php echo "$categoriesRR"; ?>
        },
        yAxis: {
            title: {
                text: ''
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '電容值'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '往前加速度',
            data: <?php echo "$data_ax"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy"; ?>
        }]
    });
});
</script>
</html>