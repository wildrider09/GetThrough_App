<?php
require 'db.php';
$query="select * from  truck ";
$response['res']=array();
$run=mysqli_query($conn,$query);
if($run){
	while($row=mysqli_fetch_array($run)){
				$res=array();
				$res['truck_id']=$row[0];
				$res['truck_name']=$row[1];
				$res['truck_rating']=$row[2];
				$res['truck_item_id']=$row[3];
				$query1="select * from truck_item_id where truck_item_id='$row[3]'";
				$run1=mysqli_query($conn,$query1);
				if($run1){
								$res['items']=array();
					while($row1=mysqli_fetch_array($run1)){
			
						$items=array();
						$items['item_name']=$row1[1];
						$items['item_price']=$row1[2];
						array_push($res['items'],$items);
					}
				}
				array_push($response['res'],$res);
	}
}
die(json_encode($response));
?>