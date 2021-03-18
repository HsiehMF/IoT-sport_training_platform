<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
</head>
<body>
<div id="container_right"></div>
<div id="container_left"></div>
<div id="container_heart"></div>
<button id="angel">返回</button>
<?php 
$player_uid=$_GET["player_uid"];
$schedule_date=$_GET["schedule_date"];
$list_num=$_GET["list_num"];
$item_name=$_GET["item_name"];
$item_time=$_GET["item_time"];

if ($item_name=="跑步") {
    $item_name="running";
}

$schedule_date=explode(" ",$schedule_date);
// $list_num=$list_num-1;
$filename_right="$player_uid"."_"."$schedule_date[0]"."_"."$list_num"."_"."$item_name"."_0".".txt";
$filename_left="$player_uid"."_"."$schedule_date[0]"."_"."$list_num"."_"."$item_name"."_1".".txt";
$filename_heartrate="$player_uid"."_"."$schedule_date[0]"."_"."$list_num"."_"."$item_name"."_heartrate".".txt";

$fp=fopen("../uploads/upload/$filename_right","r");
$data_array[0][0]=0;
// $num=5;//for loop length
$data_ax="[";
$data_gy="[";
$categories="[";
$j=1;//Y軸
$temp_ax_1;
$temp_gx_1;
$k=0;
while ($mydata=fgets($fp,60)) {
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
    $data_ax="$data_ax"."$line_data[1],";
    $data_gy="$data_gy"."$line_data[3],";
    $categories="$categories"."$j,";
    $j++;
    $k++;
}
$data_ax="$data_ax"."]";
$data_gy="$data_gy"."]";
$categories="$categories"."]";
// echo "$categories";
fclose($fp);


// echo "$data_ax"."<br>"."$data_gy";
$fp1=fopen("../uploads/upload/$filename_left","r");
$data_array[0][0]=0;
// $num=5;//for loop length
$data_ax_1="[";
$data_gy_1="[";
$categories1="[";
$temp_ax;
$temp_gx;
$j=1;//Y軸
$k=1;
while ( $mydata=fgets($fp1,60)) {

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
    $categories1="$categories1"."$j,";
    $j++;
    $k++;
 } 
$data_ax_1="$data_ax_1"."]";
$data_gy_1="$data_gy_1"."]";
$categories1="$categories1"."]";
// echo "$categories";
fclose($fp1);
// echo "$data_ax"."<br>"."$data_gy";


$t=1;
$fp1=fopen("../uploads/upload/$filename_heartrate","r");
$data_heartrate="[";
$categories2="[";
while ( $mydata=fgets($fp1,10)) {
	$data_heart=$mydata;
	$data_heart=(int)$data_heart;
	if ($data_heart==0) {
		continue;
	}
	if ($data_heart>220) {
		continue;
	}
    $data_heartrate="$data_heartrate"."$data_heart,";
    $categories2="$categories2"."$t,";
    $t++;
}
$categories2="$categories2"."]";
$data_heartrate=$data_heartrate."]";
fclose($fp1);
?>

<script type="text/javascript">
//假如网页当中有一个id为angel的按钮
$("#angel").click(function(){
   window.history.go(-1);
});
</script>

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
            categories: <?php echo "$categories"; ?>
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
        }]
    });
});
</script>
<script type="text/javascript">
	$(function () {
    $('#container_left').highcharts({
        title: {
            text: '左腳變化量',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            // categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May']
            categories: <?php echo "$categories1"; ?>
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
            data: <?php echo "$data_ax_1"; ?>
        }, {
            name: '左右加速度',
            data: <?php echo "$data_gy_1"; ?>
        }]
    });
});
</script>


<script type="text/javascript">
    $(function () {
    $('#container_heart').highcharts({
        title: {
            text: '心律變化量',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            // categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May']
            categories: <?php echo "$categories2"; ?>
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
            valueSuffix: '下'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '心律',
            data: <?php echo "$data_heartrate"; ?>
        }]
    });
});
</script>

</body>
</html>