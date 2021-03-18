 <?php  
 require "init.php";  
   
   $coach_id = $_POST['coach_id']; 
   $sql_query = "SELECT course_name FROM `Course` , `User` WHERE Course.UID = User.UID and Course.UID = '$coach_id'";
   $result = mysqli_query($con,$sql_query);  
     while ($row = mysqli_fetch_array($result)) {
       echo $row["course_name"];
       echo ",";
     }
 ?>
