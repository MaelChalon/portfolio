<?php
session_start();
use app\Database;
use app\Form;

require_once __DIR__ . "/vendor/autoload.php";

$form = new Form();
$database = new Database();

ob_start();

$path = $_SERVER['PATH_INFO']??"/";
if (!isset($_SESSION['user']) && $path != "/login") {
    header("Location: /login");
    exit();
}

switch($path){
    case "/":
        include __DIR__ . "/index.php";
        break;
    case "/login":
        if($_SERVER["REQUEST_METHOD"] == "GET"){
            include __DIR__ . "/login.php";
        }else{
            include __DIR__ . "/traitement-form-connect.php";
        }
        break;
    case "/todo-list":
        include __DIR__ . "/todo-list.php";
        break;
    case "/logout":
        include __DIR__ . "/logout.php";
        break;
    case "/form-edit":
        if($_SERVER["REQUEST_METHOD"] == "GET"){
            include __DIR__ . "/form-edit.php";
        }else{
            include __DIR__ . "/traitement-form-add-ticket.php";
        }
        break;
    default:
        header('HTTP/1.0 404 Not Found');
        echo "erreur 404";
        exit;
    
    $affiche = ob_get_clean();
    echo $affiche;
}