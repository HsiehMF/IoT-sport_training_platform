 <?php  
 require "init.php";  
   
   $sql_query = "SELECT c_name FROM `COURSE` WHERE 1";  
   $result = mysqli_query($con,$sql_query);  
     while ($row = mysqli_fetch_array($result)) {
       echo $row["c_name"];
       echo ",";
     }
 ?>
