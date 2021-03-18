<?php

  require "../init.php";
  $UID = $_POST["UID"];

  $sql = "SELECT `Course`.`course_num` FROM `User`,`Course` WHERE `User`.`UID`='$UID' and `User`.`UID` = `Course`.`UID`";
  $result = mysqli_query($con,$sql);
  while($row = mysqli_fetch_array($result)){
    $num = $row["course_num"].",";
    $str = $str.$num;
  }

  $array = explode(",", $str);
  $j = (int)count($array)-1; // count 陣列數量 , 最後一值為空 , 所以數量減一
  for( $i=0 ; $i<$j ; $i++ ){ 
    $course_num = $array[$i];
    $sql = "SELECT `NAME` FROM `User` WHERE `course_num` = '$course_num' and `COACH` = '0'";
    $result = mysqli_query($con,$sql);
      while($row = mysqli_fetch_array($result)){
        echo $row["NAME"];
        echo ",";
      }
  }

?>
