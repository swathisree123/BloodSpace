<?PHP
 
include("conn.php");

if(isset($_POST['name']) && isset($_POST['bldgrp'])&& isset($_POST['phnno'])&& isset($_POST['dob'])&& isset($_POST['city'])&& isset($_POST['state']))
{
 	$name= $_POST["name"];

    $bldgrp=$_POST["bldgrp"];

    $phnno=$_POST["phnno"];

	$dob=$_POST["dob"];

	$city=$_POST["city"];
	
	$state=$_POST["state"];


    
 $result = mysqli_query($conn, "SELECT phnno FROM details WHERE phnno = '".$phnno."'"); 
 if($result && mysqli_num_rows($result) > 0)
 { 
 echo "phonenumber exist";
 exit;
 } 
 else
 { 
      $query="INSERT INTO details(name,bldgrp,phnno,dob,city,state)VALUES('$name','$bldgrp','$phnno','$dob','$city','$state')";
 
      $data=mysqli_query($conn,$query);
 
    if($data)
      {
            echo "Successfully Registered";
      }
    else
      {
            echo "Error";
      }
 
 exit;
 } 
} 
 
?>