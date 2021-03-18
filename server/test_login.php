<?php
require "init.php";
 $user_name = $_POST["ID"];
 $user_pass =  $_POST["PWD"];
 $sql_query = "select NAME from User where ID like '$user_name' and PWD like '$user_pass';"; 
 $result = mysqli_query($con,$sql_query);
 if(mysqli_num_rows($result) >0 )
 {
 $row = mysqli_fetch_assoc($result);
 echo $row["NAME"];
}
 else
 {
echo "Login Fail.Please Try Again";
 }
 ?>
