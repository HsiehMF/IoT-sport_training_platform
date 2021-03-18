<?php

 $dbServer ="localhost";
 $dbUser = "root";
 $dbPass = "2u04j;3yji4openvas";
 $dbName = "webappdb";
 
 $con = @mysqli_connect($dbServer,$dbUser,$dbPass,$dbName);
 if(mysqli_connect_errno($con))
  die("error");
  mysqli_set_charset($con,"utf8");
 
 ?>  
