 <?php  
 require "init.php";  
	// 教練UID   
   $sql = "SELECT `schedule_name` FROM `Schedule` WHERE `course_num`='2'";  

   $result = mysqli_query($con,$sql);  
     while ($row = mysqli_fetch_array($result)) {
       echo $row["schedule_name"];
       echo ",";
     }
 ?>
