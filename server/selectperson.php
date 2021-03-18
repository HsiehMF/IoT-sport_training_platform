 <?php  
 require "init.php";  
   
   $course_name = $_POST['course_name']; 
   $sql_query = "SELECT NAME FROM `Course`,`User` WHERE  Course.course_num = User.course_num and User.COACH !='1' and Course.course_name = '$course_name'";
   $result = mysqli_query($con,$sql_query);  
     while ($row = mysqli_fetch_array($result)) {
       echo $row["NAME"];
       echo ",";
     }
 ?>
