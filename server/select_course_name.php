 <?php  
 require "init.php";  
   
   $course_name = $_POST['course_name']; 
   $UID = $_POST["UID"];

   $sql_query = "SELECT course_num FROM `Course` WHERE course_name = '$course_name' and UID='$UID'";
   $result = mysqli_query($con,$sql_query);  
   while ($row = mysqli_fetch_array($result)) {
       echo $row["course_num"];
   }
 ?>
