<?PHP
 
include("conn.php");

if(isset($_POST['bldgrp']) && isset($_POST['city']))
{

 
    $bldgrp=$_POST["bldgrp"];
    
    $city=$_POST["city"];

$r = mysqli_query($conn,"SELECT * FROM details where bldgrp = '".$bldgrp."' AND city = '".$city."' ");


$result = array();

while($row = mysqli_fetch_array($r)){
    array_push($result,array(
        'name'=>$row['name'],
        'bldgrp'=>$row['bldgrp'],
        'city'=>$row['city'],
        'phnno'=>$row['phnno']
    ));
}
}
echo json_encode(array('result'=>$result));

?>