<?php

if(isset($_POST['post'])){
	file_put_contents("data.json",$_POST['post']);
}

?>