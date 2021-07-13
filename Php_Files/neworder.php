<?php
require 'db.php';
$items_array=array();
	$price_array=array();
if($conn&&isset($_REQUEST)){
	$item_id=date("h:i:sa");
	$truck_id=$_REQUEST['truck_id'];
	$items=$_REQUEST['items'];
	$item_price=$_REQUEST['prices'];
	$student_id=$_REQUEST['student_id'];
	$items_array=explode(",",$items);

$order_amt=0.0;
$price_array =explode(",",$item_price);


for($z=0;$z<sizeof($items_array);$z++){

	$order_name=$items_array[$z];
	$order_price=$price_array[$z];
	$order_amt=doubleval($order_amt)+doubleval($price_array[$z]);
	$query="insert into order_items values('$item_id','$order_name','$order_price')";
	$run=mysqli_query($conn,$query);
	
}




$status="pending";
$query_final="insert into food_order values('$item_id','$order_amt','$status','$item_id','$student_id','$truck_id')";
$run_final=mysqli_query($conn,$query_final);
if($run_final){
	echo "order placed";
}else{
	echo "failed";
}


	
}else{
	
}

?>