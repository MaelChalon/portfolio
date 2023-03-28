<?php
include "include/start.php";

$CreateEleve = $db->query("call CreateIME(:IME, :desc)", array("IME" => "IME1", "desc" => "quelque part"));
$CreateEleve = $db->query("call CreateIME(:IME, :desc)", array("IME" => "IME2", "desc" => "quelque part"));

$db->CreateAdministrateur("Amin", "Pierre", "123");//1
$db->CreateDirecteur("Peter", "Jean", "123");//2
$db->CreateEducateur("Badeaux", "Edgar", "123");//3
$db->CreateEducateur("Périer", "Guillaume", "123");//4
$db->CreateEleve("LORTIE", "Florent", "123");//5
$db->CreateEleve("MOITESSIER", "Killian", "123");//6
$db->CreateEleve("GERALD", "Laurine", "0000");//7
$db->CreateEducateur("Badeaux", "Edgar", "321");//8
$db->CreateEducateur("Martin", "Julie", "123");//9
$db->CreateEducateur("Dupont", "Michel", "123");//10

$CreateEleve = $db->query("call enseigne(:id,:idIME)", array("id" => 4, "idIME" => 1));
$CreateEleve = $db->query("call enseigne(:id,:idIME)", array("id" => 3, "idIME" => 1));
$CreateEleve = $db->query("call enseigne(:id,:idIME)", array("id" => 8, "idIME" => 1));
$CreateEleve = $db->query("call enseigne(:id,:idIME)", array("id" => 9, "idIME" => 1));
$CreateEleve = $db->query("call enseigne(:id,:idIME)", array("id" => 10, "idIME" => 1));

$prep = $db->query("call etudie(:id,:idIME)", array("id" => 5, "idIME" => 1));
$prep = $db->query("call etudie(:id,:idIME)", array("id" => 5, "idIME" => 2));
$prep = $db->query("call etudie(:id,:idIME)", array("id" => 6, "idIME" => 1));
$prep = $db->query("call etudie(:id,:idIME)", array("id" => 7, "idIME" => 1));
$prep = $db->query("call etudie(:id,:idIME)", array("id" => 7, "idIME" => 2));


$prep = $db->query("call createCategorie(:nom, :desc, :idIME)", array("nom" => "Motricité", "desc" => "Capacité motrices (physique)", "idIME" => 1));
$prep = $db->query("call createCategorie(:nom, :desc, :idIME)", array("nom" => "test de cat", "desc" => "test de cat", "idIME" => 1));
$prep = $db->query("call createCategorie(:nom, :desc, :idIME)", array("nom" => "Réflexion", "desc" => "Capacité de réflexion (intellectuel)", "idIME" => 2));
$prep = $db->query("call createCategorie(:nom, :desc, :idIME)", array("nom" => "Motricité", "desc" => "Capacité motrices (physique)", "idIME" => 2));


$ListeIdcat = array();
array_push($ListeIdcat, 1);
array_push($ListeIdcat, 2);
$db->createCompetence("Distribuer carte", "Savoir distribuer des cartes une par une", $ListeIdcat);
$ListeIdcat = array();
array_push($ListeIdcat, 2);
$db->createCompetence("Reconnaitre carte", "Savoir reconnaître la couleur, le numéro et toutes les informations d une carte", $ListeIdcat);
$ListeIdcat = array();
array_push($ListeIdcat, 2);
$db->createCompetence("Compter (1-20)", "Savoir compter de 1 à 20", $ListeIdcat);

$prep = $db->query("select createActivite(:nom, :desc, :idIME)", array("nom" => "UNO", "desc" => "Jeux de société UNO : le but est de ne plus avoir de cartes", "idIME" => 1));
$prep = $db->query("select createActivite(:nom, :desc, :idIME)", array("nom" => "La Bataille", "desc" => "Jeux de société : le but est d avoir la carte la plus forte", "idIME" => 1));


$prep = $db->query("call competenceActivite(:idComp, :idAc, 1,0)", array("idComp" => 1, "idAc" => 1));
$prep = $db->query("call competenceActivite(:idComp, :idAc, 1,0)", array("idComp" => 2, "idAc" => 1));
$prep = $db->query("call competenceActivite(:idComp, :idAc, 1,0)", array("idComp" => 3, "idAc" => 1));
$prep = $db->query("call competenceActivite(:idComp, :idAc, 1,0)", array("idComp" => 1, "idAc" => 2));
$prep = $db->query("call competenceActivite(:idComp, :idAc, 1,0)", array("idComp" => 2, "idAc" => 2));


$prep = $db->query("select createActiviteDuMoment(:id)", array("id" => 1));

$prep = $db->query("call joue(:idPer,:idJouer)", array("idPer" => 5, "idJouer" => 1));
$prep = $db->query("call joue(:idPer,:idJouer)", array("idPer" => 6, "idJouer" => 1));
$prep = $db->query("call joue(:idPer,:idJouer)", array("idPer" => 7, "idJouer" => 1));

$prep = $db->query("call gere(:idPer,:idJouer)", array("idPer" => 3, "idJouer" => 1));
$prep = $db->query("call gere(:idPer,:idJouer)", array("idPer" => 4, "idJouer" => 1));
$prep = $db->query("call gere(:idPer,:idJouer)", array("idPer" => 3, "idJouer" => 2));


echo "Si vous voyez ca c'est que tout marche bien ;)";