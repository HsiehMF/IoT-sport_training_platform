 <?php  
 require "init.php";  
 
   $sql_query = "SELECT NAME FROM `USER` WHERE 1";  
   $result = mysqli_query($con,$sql_query);  
     while ($row = mysqli_fetch_array($result)) {
       echo $row["NAME"];
       echo ",";
     }
 ?>
