<?php
// $UID = $_POST["UID"];
// $curDay = $_POST["curDay"];
$filename=$_POST["filename"];
$dir="./upload/";
$i=count($_FILES["upload"]["name"]);
for($j=0;$j<$i;$j++){
        $tmpname=$_FILES["upload"]["tmp_name"];
//        $filename=$_FILES["upload"]["name"][$j];
        move_uploaded_file($tmpname, $dir.$filename);
        $ree= $dir.$filename.".txt";
}
echo $ree;
// echo "<img src='$ree'>";
?>

