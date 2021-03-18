<?php
$host='140.127.218.198';
$username='root';
$pwd='2u04j;3yji4openvas';
$db="webappdb";
$con=mysqli_connect($host,$username,$pwd,$db) or die('Unable to connect');

if(mysqli_connect_error($con))
{
  echo "Failed to Connect to Database ".mysqli_connect_error();
}

$response = $_POST['response'];
$sql="select INFO from USER where NAME=('$response')";

// "insert into user_info values('$name','$user_name','$user_pass');";

$query=mysqli_query($con,$sql);


if($query)
{
    while($row=mysqli_fetch_array($query))
  {
    $data[]=$row;
  }

    print(json_encode($data));


}else
{
  echo('Not Found ');
}
mysqli_close($con);

?>








