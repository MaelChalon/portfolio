<?php

    $listCompetence = [];
    if(isset($_POST['data'])){
        $listCompetence = json_decode($_POST['data']);
        
    }
    if($_SESSION['ActiviteData']['refreshData']) {
        $listCompetence = $_SESSION['ActiviteData']['listeCompetence'];
    }
    if($listCompetence != [] && $listCompetence != "") :
        
        foreach($listCompetence as $comp) :
?>
<div class='NALCID_<?php if($_SESSION["ActiviteData"]["refreshData"]) echo $comp["idCompetence"]; else echo $comp->idCompetence; ?> flex_row flex_double_center space_between list_item_style_2'>
    <p class='NALCID_<?php if($_SESSION["ActiviteData"]["refreshData"]) echo $comp["idCompetence"]; else echo $comp->idCompetence; ?>'><?php if($_SESSION["ActiviteData"]["refreshData"]) echo $comp["nom"]; else echo $comp->nom; ?></p>
    <a href='#' onclick='removeCompetence(<?= json_encode($comp) ?>)' class='NALCID_<?php if($_SESSION["ActiviteData"]["refreshData"]) echo $comp["idCompetence"]; else echo $comp->idCompetence; ?>'><span class="close_window"></span></a>
    <input type="hidden" id='NALCID_<?php if($_SESSION["ActiviteData"]["refreshData"]) echo $comp["idCompetence"]; else echo $comp->idCompetence; ?>' name="idCompetences[]" value="<?php if($_SESSION["ActiviteData"]["refreshData"]) echo $comp["idCompetence"]; else echo $comp->idCompetence; ?>">
</div>
<?php
    endforeach;
    
endif;
// if($_SESSION['ActiviteData']['refreshData']) {
//     $_SESSION['ActiviteData']['refreshData'] = false;
//     $_SESSION['ActiviteData']['nom'] = "";
//     $_SESSION['ActiviteData']['description'] = "";
//     $_SESSION['ActiviteData']['listeCompetence'] = array();
//     $_SESSION['ActiviteData']['isModifyActity'] = false;
//     $_SESSION['ActiviteData']['idActiviteToModify'] = -1;
// }
?>