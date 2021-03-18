<?php
 require "init.php";

   $sql_query = "SELECT NAME FROM USER";
   $result = mysqli_query($con,$sql_query);
     while ($row = mysqli_fetch_array($result)) {
       echo $row["NAME"];
       echo ",";
     }
 ?>


