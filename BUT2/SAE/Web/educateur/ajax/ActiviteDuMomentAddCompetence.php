<?php

$listeCompetence = $_POST["data"];

$listeCompetence = json_decode($listeCompetence, true);

$listeIdCompetence = array();

foreach($listeCompetence as $comp) {
    array_push($listeIdCompetence, $comp['idCompetence']);
}

/** @var Database $db */
$db->modifyActiviteDuMoment($_SESSION["idActiviteDuMoment"], $listeIdCompetence);