<DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<head>
        <meta charset="UTF-8" />
        <title></title>

<style>
table, th, td {
    border: 1px solid black;
}
</style>
<?php
        session_start();
//       	session_register('login');
        require "init.php";
        $user_name = $_POST["ID"];
        $user_pass =  $_POST["PWD"];
        $COACH;
        $UID;
        $sql_query = "select UID,NAME,COACH from User where ID like '$user_name' and PWD like '$user_pass'";
        $result = mysqli_query($con,$sql_query);
        if(mysqli_num_rows($result) != 0 ) {
                 $row = mysqli_fetch_assoc($result);
                 $UID =$row["UID"];
                 $COACH=$row["COACH"];
                 // echo $UID."<br>".$COACH;
                 if($COACH==0){
                    $_SESSION['login'] ="player";
                    $_SESSION['UID'] ="$UID";
                 }else{
                    $_SESSION['login'] ="coach";
		   		 	$_SESSION['UID'] ="$UID";
                 }

        }
        else{
                header('Location: index.php');
        }
?>
</head>
<body>
<div id="container" style="width: 600px; height: 400px; margin: 0 auto"> 
</div>
 <?php
    if($COACH==0){//非coach

       header('Location: player.php');

        }else{
            header('Location: coach.php');
        }
 ?>


</body>
</html>

