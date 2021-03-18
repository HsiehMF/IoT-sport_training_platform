 <?php  
 require "init.php";  
   
   $title = $_POST["title"];
   $sql_query = "SELECT * FROM `COURSE` WHERE c_name = '$title'";  
   $result = mysqli_query($con,$sql_query);  
     while ($row = mysqli_fetch_array($result)) {
       echo $row["c_name"].",".$row["c_item_name"].",".$row["c_item_sub_name"].",".$row["c_item_times"]."次";
     }
 ?>
