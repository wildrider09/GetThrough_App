<?php
require 'db.php';
$response['res']=array();
if($conn&&isset($_REQUEST)){
$studentid=$_REQUEST['studentid'];
$password=$_REQUEST['password'];
$role=$_REQUEST['role'];
$query="select * from users where student_id='$studentid'";
$run=mysqli_query($conn,$query);
if($run){
	while($row=mysqli_fetch_array($run)){
		$res=array();
		if($row[4]==$password&&$row[10]==$role){
		$res['studentid']=$row[0];
		$res['student_name']=$row[1];
		$res['student_email']=$row[2];
		$res['student_phone']=$row[3];
		$res['student_course']=$row[5];
		$res['student_dept']=$row[6];
		$res['student_receipt']=$row[7];
		$res['pass_issued']=$row[8];
		$res['uniform_issued']=$row[9];
		$res['role']=$row[10];
		}else{
		
		}
		array_push($response['res'],$res);
	}
}
}else{
	echo "not connected";
}
die(json_encode($response));
?>