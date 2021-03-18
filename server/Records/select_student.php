<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
  <title></title>
  <link rel="stylesheet" href="select_student.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://code.highcharts.com/highcharts.js"></script>
  <script type="text/javascript" src="js/themes/gray.js"></script>
  <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
</head>
<body>
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script> -->
<script type="text/javascript" src="js/materialize.min.js"></script>
<div id="container"></div>
<DIV>

  <?php
  session_start();
  if($_SESSION['login']!="coach"){
  header('Location: index.php');
  }else{
    
    require "../init.php";
    $course_name = $_GET["course_name"];
    $UID = $_GET["UID"];
    $crowd_num = $_GET["crowd_num"];
    $crowd_name= $_GET["crowd_name"];
	
    $sql = "SELECT `schedule_num`,`schedule_name`,`schedule_date`,`Estimate` FROM `Schedule` WHERE `crowd_num`=$crowd_num ORDER BY  `Schedule`.`schedule_date` DESC ";
    $result = mysqli_query($con,$sql);

      echo '<div class="collection">';
      echo '<a class="collection-item active">'.$crowd_name.'</a></div>';

      while($row1 = mysqli_fetch_array($result)) {

        echo "<ul class='collapsible' data-collapsible='accordion'>
                <li>
                  <div class='collapsible-header active'><i class='material-icons'>assignment</i>課表名稱 : <b>$row1[schedule_name]</b></div>";
        echo '       <div class="collapsible-body">';
        
        echo "<p>制定日期 :".$row1[schedule_date]."</p>";
        echo "<p>評語 : ".$row1[Estimate]."</p>";
        echo "<a class='waves-effect waves-light btn' href='http://140.127.218.198:8080/webapp/Records/studentdetile.php?UID=$UID&course_name=$course_name&player_uid=$row1[UID]&crowd_num=$crowd_num&crowd_name=$crowd_name&schedule_num=$row1[schedule_num]&schedule_name=$row1[schedule_name]&schedule_date=$row1[schedule_date]'>點擊查看</a>";
        echo '      </div>
                </div>
              </li>
            </ul>';
    }

// ---------------------------------------------------------------------------------------
    $amount="-1";    //X 
    $date="-1,----";      //Y


    $sql_schu_num="SELECT `schedule_num`,`schedule_date` FROM `Schedule` WHERE `crowd_num`=$crowd_num ORDER BY  `Schedule`.`schedule_date` DESC ";
    $result_schu_num= mysqli_query($con,$sql_schu_num);
    $schu_num="-1";
    while($row_schu_num = mysqli_fetch_array($result_schu_num)) {
      $schu_num="$schu_num,$row_schu_num[schedule_num]";
      $date_split = explode(" ",$row_schu_num[schedule_date]);
      $date="$date,$date_split[0]";
    }
  //  echo $schu_num."<br>";
    $pieces = explode(",",$schu_num);
  //  echo count($pieces )."<br>";
 //   echo "0\t".$pieces[0]."1\t".$pieces[1]."2\t".$pieces[2]."3\t".$pieces[3]."<br>";

    for ($i=0; $i<count($pieces) ; $i++) { 
//      echo $i."<br>";
      //echo "pieces[i]= \t".$pieces[i];
      $sql_Line_num="SELECT `line_num` FROM `Line` WHERE `schedule_num`=$pieces[$i]";
      $Line_num="-1";
//      echo "sql_Line= ".$sql_Line_num."<br>";
      $result_Line_num= mysqli_query($con,$sql_Line_num);
      while($row_Line_num = mysqli_fetch_array($result_Line_num)) {
        $Line_num="$Line_num,$row_Line_num[line_num]";//fine Line num
      }
//	echo "Line= ".$Line_num."<br>";
// --------------------------------------------------------------------------------------
      $temp=0;//store the Line of quantity
      $Line = explode(",",$Line_num);
      $count_line=count($Line);
      for ($j=1; $j<$count_line ; $j++) {
	      // echo "Line[$j]".$Line[$j]."<br>"; 
        $sql_Line = "SELECT `item_id` FROM `Line` where `line_num`=$Line[$j]";
     //   echo "sql_Line= ".$sql_Line ."<br>";
	      $result_sql_Line= mysqli_query($con,$sql_Line);
        $item_id="-1";
        while($row_item_id = mysqli_fetch_array($result_sql_Line)) {
          $item_id="$item_id,$row_item_id[item_id]";//fine Line num
        }
//        echo "item_id".$item_id."<br>";
        $item=explode(",",$item_id);
        for ($k=0; $k<count($item) ; $k++) { 
          $sql_item = "SELECT `item_sub_name`,`item_times` FROM `Item` where `item_id`=$item[$k]";
          $result_sql_item=mysqli_query($con,$sql_item);
          $item_X;
          while($row_item = mysqli_fetch_array($result_sql_item)) {
            $quantity=str_replace("","公尺",$row_item[item_sub_name]);
            $times=$row_item[item_times];
            // echo "quantity=\t $quantity\t times=\t $times"."<br>";
            $temp+=$quantity*$times;
	          // echo "temp=\t".$temp."<br>";
          // $item_X="$row_item,$row_item[item_sub_name]";//fine Line num
          // echo "item_id= ".$item_id."<br>";
          } 
        }
      }//caculte the total of train quantity
      $amount="$amount,$temp";
    }
      // echo "$amount"."<br>"."$date";
  }
?>
</DIV>

<?php
  $Y=explode(",",$amount);
  $X=explode(",", $date);
  $YY;
  $XX;
  for($i=1;$i<count($Y);$i++){
	$YY[$i-1]=$Y[$i];
	$XX[$i-1]=$X[$i];
  }
  $X_Axis="'$XX[0]'";
  $Y_Axis=$YY[0];

    for ($i=1; $i <count($XX) ; $i++) { 
      $X_Axis="$X_Axis,'$XX[$i]'";
      $Y_Axis="$Y_Axis,$YY[$i]";
    }

?>

<!-- <div id="container"> --> 
  <script type="text/javascript">
    var X_Axis= "<?php echo "[$X_Axis]"; ?>";
    var Y_Axis= "<?php echo "[$Y_Axis]"; ?>";
    var chart;
    $(function () {
      $('#container').highcharts({
        chart: {type: 'bar'},
        title: {text: '訓練統計量'},
        xAxis: {categories: [ <?php echo "$X_Axis"; ?>]},
        yAxis: {title: {text: '總訓練量'}},
        series: [
          {name: ' <?php echo $crowd_name; ?>', data: [ <?php echo "$Y_Axis"; ?>]}
          ]
      });
    });
  </script>
<!-- </div> -->

</body>
</html>

