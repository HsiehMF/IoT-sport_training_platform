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
  session_start();
    if($_SESSION['login']!="coach"){
      header('Location: index.php');
    }else{
  
      require "../init.php";
      $UID = $_GET["UID"];
      $course_name = $_GET["course_name"];

      $sql = "SELECT * FROM Crowd WHERE UID = 2 and course_name = \"$course_name\"";
      $result = mysqli_query($con,$sql);
      
      echo '<ul class="collapsible" data-collapsible="accordion">
              <li>
                <div class="collapsible-header active"><i class="material-icons">supervisor_account</i>'.$course_name.'</div>';
      echo '    <div class="collapsible-body">
                  <div class="collection">
                  ';
                  while($row = mysqli_fetch_array($result)) {
                    echo "<a class='collection-item active' href='http://140.127.218.198:8080/webapp/Records/select_student.php?UID=$UID&course_name=$course_name&crowd_num=$row[crowd_num]&crowd_name=$row[crowd_name]'>$row[crowd_name]</a>";
                  }
      echo '      </div>
                </div>
              </li>
            </ul>';

      echo '<a class="waves-effect waves-light btn" href="http://140.127.218.198:8080/webapp/Records/coach.php?UID=$UID"><i class="material-icons left">fast_rewind</i>返回社團</a>';
//   

    }
?>
</body>
</html>


