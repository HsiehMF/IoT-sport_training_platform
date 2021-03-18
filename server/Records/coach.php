<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="index.css">
	<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
	<title></title>
</head>
<body>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
	<?php
		require "init.php";
		session_start();

		if($_SESSION['login']!="coach"){
			header('Location: index.php');
		}else{
		  $UID =$_SESSION['UID'];
  		
		  $sql = "SELECT `course_name` FROM `Course` WHERE `UID`='$UID'";	
		  $result = mysqli_query($con,$sql);
		
		  echo '<div class="collection">';
		  echo '<a class="collection-item active">請選擇社團</a>';
		  $i = 0;
		  $row_num = mysqli_num_rows($result);
		    while ( $i< $row_num) {
		 	  $row = mysqli_fetch_array($result,MYSQLI_ASSOC); 
		 	  echo "<a href='http://140.127.218.198:8080/webapp/Records/select_crowd.php?UID=$UID&course_name=$row[course_name]' class='collection-item'>$row[course_name]</a>";
			  $i++;
		    }
		  echo '</div>';	
		}
	?>
    
  </p>    
</body>
</html>


