<?php

$listeCompetence = $_POST["data"];
$idCategorie = $_POST["idCat"];

$listeCompetence = json_decode($listeCompetence, true);

$listeIdCompetence = array();

foreach($listeCompetence as $comp) {
    /** @var Database $db */
    $prep = $db->query("select competenceCategorie(:idComp,:idCat)", array('idComp' => $comp['idCompetence'], 'idCat' => $idCategorie));
    $prep->fetch();
}