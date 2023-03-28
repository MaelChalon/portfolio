<?php
if(isset($_POST['nomActivite']) && isset($_POST['descActivite']) && isset($_POST['listeCompetences']) && isset($_POST['isModifyActity']) && isset($_POST['idActiviteToModify'])) {
    $_SESSION['ActiviteData']['refreshData'] = true;
    $_SESSION['ActiviteData']['nom'] = json_decode($_POST['nomActivite'], true);
    $_SESSION['ActiviteData']['description'] = json_decode($_POST['descActivite'], true);
    $_SESSION['ActiviteData']['listeCompetence'] = json_decode($_POST['listeCompetences'], true);
    $_SESSION['ActiviteData']['isModifyActity'] = $_POST['isModifyActity'];
    $_SESSION['ActiviteData']['idActiviteToModify'] = $_POST['idActiviteToModify'];
}
