<?php
require 'db.php';
$student_id=$_REQUEST['vendor_id'];
$query="select * from  food_order where truck_id='$student_id' ";
$response['res']=array();
$run=mysqli_query($conn,$query);
if($run){
	while($row=mysqli_fetch_array($run)){
				$res=array();
				$res['order_id']=$row[0];
				$res['order_amt']=$row[1];
				$res['order_status']=$row[2];
			    $res['student_id']=$row[4];
				$res['truck_id']=$row[5];
				array_push($response['res'],$res);
	}
}
die(json_encode($response));
?>