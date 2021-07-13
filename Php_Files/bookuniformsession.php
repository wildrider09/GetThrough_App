<?php

require 'db.php';
if($conn&&isset($_REQUEST)){

	$student_id=$_REQUEST['student_id'];
	$year=$_REQUEST['year'];
	$date=$_REQUEST['date'];
	$slot=$_REQUEST['slot'];
	$number=$_REQUEST['number'];

	$query="insert into uniforms values('$student_id','$year','$number','$slot','$date')";
	$run=mysqli_query($conn,$query);
	if($run){
		
		$query3="update users set uniform_issued='session booked' where student_id='$student_id'";
		$ruun=mysqli_query($conn,$query3);
		if($ruun){
			echo "session booked";
		}else{
			echo "failed";
		}
	}
}else{
	echo "some error";
}

?>