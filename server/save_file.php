<?php 

//	$file_path = "images/";
//
//	$file_path = $file_path . basename($_FILES['uploaded_file']['name']);
//
//	if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'],$file_path))	{
//		echo "success";
//	}else {
//		echo "error";
//	}


//<?php
// In PHP versions earlier than 4.1.0, $HTTP_POST_FILES should be used instead
// of $_FILES.

$uploaddir = '/var/www/uploads/';
$uploadfile = $uploaddir . basename($_FILES['userfile']['name']);

echo '<pre>';
if (move_uploaded_file($_FILES['userfile']['tmp_name'], $uploadfile)) {
   echo "File is valid, and was successfully uploaded.\\\\n";
} else {
   echo "Possible file upload attack!\\\\n";
}

echo 'Here is some more debugging info:';
print_r($_FILES);

print "</pre>";

?>


