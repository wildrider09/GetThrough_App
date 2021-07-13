<?php

require 'db.php';
if($conn&&isset($_REQUEST)){
	$passno=$_REQUEST['passno'];
	$student_id=$_REQUEST['student_id'];
	$student_name=$_REQUEST['stu_name'];
	$fathers_name=$_REQUEST['fathers_name'];
	$validity="valid till ".date("Y");
	$query="insert into buspass values('$passno','$student_id','$student_name','$fathers_name','$validity')";
	$run=mysqli_query($conn,$query);
	if($run){
		
		$query3="update users set pass_issued='issued' where student_id='$student_id'";
		$ruun=mysqli_query($conn,$query3);
		if($ruun){
			echo "pass created";
		}
	}
}else{
	echo "some error";
}

?>