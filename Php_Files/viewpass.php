<?php
require 'db.php';
if($conn){
	$response['res']=array();
	$student_id=$_REQUEST['student_id'];
	$query="select * from buspass where student_id='$student_id'";
	$run=mysqli_query($conn,$query);
	if($run){
		while($row=mysqli_fetch_array($run)){
			$res=array();
			$res['passno']=$row[0];
	$res['student_id']=$row[1];
	$res['student_name']=$row[2];
	$res['fathers_name']=$row[3];
	$res['validity']=$row[4];
	array_push($response['res'],$res);
	
		}
	}
}
die(json_encode($response));
?>