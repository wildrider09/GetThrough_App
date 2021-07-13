<?php
require 'db.php';
$order_id=$_REQUEST['order_id'];
$order_handle=$_REQUEST['order_handle'];

$query="update  food_order  SET order_status='$order_handle' WHERE order_id='$order_id'";
$run=mysqli_query($conn,$query);
if($run){
	echo "updated";
}

?>