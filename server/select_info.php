<?php
  require "init.php"; 
  $selected_student_name = $_POST["selected_student_name"];
  $sql = "SELECT NAME,INFO FROM User WHERE NAME='$selected_student_name'";
  
  $result = mysqli_query($con,$sql);
  while($row = mysqli_fetch_array($result))
  {
      echo $row["NAME"].",".$row["INFO"];
  }


?>
