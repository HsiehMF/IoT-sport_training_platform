<?php
header('Content-Type:text/html;charset=utf8');
require "init.php";

   $coach_id = $_POST["coach_id"];
   $sql = "SELECT course_name FROM  `Course` ,  `User` WHERE Course.UID = User.UID and Course.UID = '$coach_id' "; 
  $result = mysqli_query($con,$sql);
if($result){
     while ($row = mysqli_fetch_array($result)) {
       echo $row['course_name'];
       echo ",";
	 }
}
else
{
  echo('Not Found ');
}
mysqli_close($con);

 ?>

