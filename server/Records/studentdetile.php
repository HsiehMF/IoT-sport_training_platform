<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <title></title>
<link rel="stylesheet" href="select_student.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
  <script src="http://code.highcharts.com/highcharts.js"></script>
</head>
<body>
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script> -->
<script type="text/javascript" src="js/materialize.min.js"></script>  


<div id="container"> 
  <script type="text/javascript">  

<?php
  require "../init.php";
  $UID = $_GET["UID"];
  $crowd_name= $_GET["crowd_name"];
  $course_name = $_GET["course_name"];
  $player_uid= $_GET["player_uid"];
  $crowd_num = $_GET["crowd_num"];
  $schedule_num=$_GET["schedule_num"];
  $schedule_name=$_GET["schedule_name"];
  $schedule_date=$_GET["schedule_date"];
?>

$(function () {
      $('#container').highcharts({
        chart: {type: 'bar'},
        title: {text: '訓練統計量'},
        xAxis: {categories: ['滿意度', '完成度', '疲勞度']},
        yAxis: {title: {text: '自評評估'}},
        series: [
                  <?php
                      require "../init.php";

                      $sql = "SELECT `NAME`,`User`.`UID` FROM `User` ,(SELECT `UID` FROM `History_Train_Crowd` WHERE `crowd_num`=(SELECT `crowd_num` FROM `Schedule` WHERE `schedule_num`=$schedule_num) and `schedule_num` = $schedule_num)as x WHERE x.`UID`=`User`.`UID`";
                      $result = mysqli_query($con, $sql);
                        while($row = mysqli_fetch_array($result)) {

                          $NAME = $row[NAME];

                          $sql1 = "SELECT `grades` FROM `Self_evaluation` WHERE `UID` = $row[UID] and `schedule_num` = $schedule_num";
                          $result1 = mysqli_query($con, $sql1);
                          $row1 = mysqli_fetch_array($result1);
                          $grades = $row1[grades];
                          echo "{name: '$NAME', data: [$grades]},";

                        }
                  ?> ]
      });
    });
  </script>
</div>

	<?php
	session_start();
  if($_SESSION['login']!="coach"){
  header('Location: index.php');
  }else{
  	require "../init.php";
	  $UID = $_GET["UID"];
	  $crowd_name= $_GET["crowd_name"];
	  $course_name = $_GET["course_name"];
    $player_uid= $_GET["player_uid"];
    $crowd_num = $_GET["crowd_num"];
    $schedule_num=$_GET["schedule_num"];
    $schedule_name=$_GET["schedule_name"];
    $schedule_date=$_GET["schedule_date"];

    $sql = "SELECT `NAME`,`User`.`UID` FROM `User` ,(SELECT `UID` FROM `History_Train_Crowd` WHERE `crowd_num`=(SELECT `crowd_num` FROM `Schedule` WHERE `schedule_num`=$schedule_num) and `schedule_num` = $schedule_num)as x WHERE x.`UID`=`User`.`UID`";

    $sql_E="SELECT `Estimate` FROM `Schedule` where `schedule_num`=$schedule_num";
    $result_E = mysqli_query($con,$sql_E);
	  $row_E = mysqli_fetch_array($result_E);

    $result = mysqli_query($con, $sql);

    echo '<div class="collection">';
      echo "<a class='collection-item active'>課表名稱 : $schedule_name &nbsp&nbsp&nbsp&nbsp 評語 : $row_E[Estimate]</a></div>";
	  
      echo "<table class='highlight'>";
      echo "<tr>";
      echo "<th>姓名</td>";
      echo "<th>自我滿意度</th>";
      echo "<th>課程滿意度</th>";
      echo "<th>選手疲勞度</th>";
      echo "<th>群組標記</th>";
      echo "<th>課表</th>";
      echo "</tr>";

    echo '<form class="col s12" action="local.php" method="POST">';
    echo "<input type='hidden' name='schedule_num' value='$schedule_num'>";
    while($row = mysqli_fetch_array($result)) {

      $sql2="SELECT `grades` FROM `Self_evaluation` WHERE `UID`=$row[UID] and `schedule_num`=$schedule_num";
      $result1 = mysqli_query($con,$sql2);

        if ($row1 = mysqli_fetch_array($result1)){

            $grades = explode(",", $row1[grades]);

            if (empty($grades[0] + $grades[1] + $grades[2])) {

                $sql3 = "SELECT `crowd_name` FROM `Crowd` WHERE `course_name`='$course_name' and `UID`='$UID'";
                $sql4 = "SELECT `mark` FROM `Crowd_member` WHERE `NAME` = '$row[NAME]' and `crowd_num` = '$crowd_num'";

                echo "<tr>";
                echo "<td>$row[NAME]</td>";
                echo "<td> </td>";
                echo "<td> </td>";
                echo "<td> </td>";
                echo "<td>
                        <div class='input-field col s12'>
                          <select class='browser-default' name='$row[NAME]'>";
                $result4 = mysqli_query($con, $sql4);
                $row4 = mysqli_fetch_array($result4);
                    // 如果標註等於 null , 則讓它為空值；如果標註找不到, 表示選手已換群組
                    if ($row4[mark] == "null") {  
                      echo "<option value='' disabled selected>(未標註)</option>";
                    } else if ($row4[mark] == "") {
                      echo "<option value='' disabled selected>(已更換群組)$row4[mark]</option>";
                    } else {
                      echo "<option value='' disabled selected>(已標註)$row4[mark]</option>";
                    }
                $result3 = mysqli_query($con, $sql3);
                  while ($row3 = mysqli_fetch_array($result3)) {
                    echo  "<option value='$row3[crowd_name],$row[UID]'>$row3[crowd_name]</option>";
                  }
                echo     "</select>
                        </div>
                      </td>";
            		echo "<td><a class='waves-effect waves-light btn' href='http://140.127.218.198:8080/webapp/Records/student_schudule.php?player_uid=$row[UID]&player_name=$row[NAME]&schedule_num=$schedule_num&schedule_name=$schedule_name&schedule_date=$schedule_date'>查看</a></td>";
                echo "</tr>";

            } else {

                $sql3 = "SELECT `crowd_name` FROM `Crowd` WHERE `course_name`='$course_name' and `UID`='$UID'";
                $sql4 = "SELECT `mark` FROM `Crowd_member` WHERE `NAME` = '$row[NAME]' and `crowd_num` = '$crowd_num'";

                echo "<tr>";
                echo "<td>$row[NAME]</td>";
                echo "<td>".$grades[0]."</td>";
                echo "<td>".$grades[1]."</td>";
                echo "<td>".$grades[2]."</td>";
                echo "<td>
                        <div class='input-field col s12'>
                          <select class='browser-default' name='$row[NAME]'>";
                $result4 = mysqli_query($con, $sql4);
                $row4 = mysqli_fetch_array($result4);
                    // 如果標註等於 null , 則讓它為空值；如果標註找不到, 表示選手已換群組
                    if ($row4[mark] == "null") {  
                      echo "<option value='' disabled selected>(未標註)</option>";
                    } else if ($row4[mark] == "") {
                      echo "<option value='' disabled selected>(已更換群組)$row4[mark]</option>";
                    } else {
                      echo "<option value='' disabled selected>(已標註)$row4[mark]</option>";
                    }
                $result3 = mysqli_query($con, $sql3);
                  while ($row3 = mysqli_fetch_array($result3)) {
                    echo  "<option value='$row3[crowd_name],$row[UID]'>$row3[crowd_name]</option>";
                  }
                echo     "</select>
                        </div>
                      </td>";
           	 	  echo "<td><a class='waves-effect waves-light btn' href='http://140.127.218.198:8080/webapp/Records/student_schudule.php?player_uid=$row[UID]&player_name=$row[NAME]&schedule_num=$schedule_num&schedule_name=$schedule_name&schedule_date=$schedule_date'>查看</a></td>";
                echo "</tr>";
            }

        } else {

            $sql3 = "SELECT `crowd_name` FROM `Crowd` WHERE `course_name`='$course_name' and `UID`='$UID'";
            $sql4 = "SELECT `mark` FROM `Crowd_member` WHERE `NAME` = '$row[NAME]' and `crowd_num` = '$crowd_num'";

            echo "<tr>";
            echo "<td>$row[NAME]</td>";
            echo "<td> </td>";
            echo "<td> </td>";
            echo "<td> </td>";
            echo "<td>
                        <div class='input-field col s12'>
                          <select class='browser-default' name='$row[NAME]'>";
                $result4 = mysqli_query($con, $sql4);
                $row4 = mysqli_fetch_array($result4);
                    // 如果標註等於 null , 則讓它為空值；如果標註找不到, 表示選手已換群組
                    if ($row4[mark] == "null") {  
                      echo "<option value='' disabled selected>(未標註)</option>";
                    } else if ($row4[mark] == "") {
                      echo "<option value='' disabled selected>(已更換群組)$row4[mark]</option>";
                    } else {
                      echo "<option value='' disabled selected>(已標註)$row4[mark]</option>";
                    }
                $result3 = mysqli_query($con, $sql3);
                  while ($row3 = mysqli_fetch_array($result3)) {
                    echo  "<option value='$row3[crowd_name],$row[UID]'>$row3[crowd_name]</option>";
                  }
            echo     "</select>
                    </div>
                  </td>";
            echo "<td><a class='waves-effect waves-light btn' href='http://140.127.218.198:8080/webapp/Records/student_schudule.php?player_uid=$row[UID]&player_name=$row[NAME]&schedule_num=$schedule_num&schedule_name=$schedule_name&schedule_date=$schedule_date'>查看</a></td>";
            echo "</tr>";

        }

        echo '      </div>
                </div>
              </li>
            </ul>';
    }

    echo "<a class='waves-effect waves-light btn' href='http://140.127.218.198:8080/webapp/Records/group_student_schudule.php?schedule_num=$schedule_num&schedule_name=$schedule_name&schedule_date=$schedule_date'>查看整體數據</a>";

    echo "</table>";
    
    echo "<div class='input-field col s6'>
            <input id='input_text' class='text' type='text' length='16'>
            <label for='input_text'>輸入課表評語</label>
          </div>";
    echo "<input class='fix' type='button' value='插入評語'>";
    echo "<input type='submit' value='儲存標記'>";
    echo "</form>";
  }
?>

<div class="input_fields_wrap"></div>
<script type="text/javascript">
var value;
$(document).ready(function(){
  $(".fix").click(function(){
      value="\""+$(".text").val()+"\"";
      $.ajax({url:"updateschudule.php", type:"POST",data:{schedule_num:"<?php echo "$schedule_num"; ?>",Estimate: "<?php   echo "$Estimate"; ?>",judge:" <?php echo "$judge"; ?>",data:value},error: function(xhr) {},success: function(result){ alert(result+"插入成功!!!") ;} 
       })
     location.reload();
  });
  
});
</script>
</body>
</html>