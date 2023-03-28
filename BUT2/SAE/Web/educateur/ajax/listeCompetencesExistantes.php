<?php
/** @var Database $db */
$result = $db->getCompetences($Personne["idIMESelected"]);

$competenceList = [];
// if ($_SERVER["PHP_SELF"] == "/educateur/ajax/tableauActiviteDuMoment.php") {
//     $competenceList = $db->getCompetencesADM($Personne["idIMESelected"],$_SESSION["idActiviteDuMoment"]);
// }else{
    $competenceList = $result;
// }

/*
recherche
if(isset($_POST['data'])) {
    if(json_decode($_POST['data'], true) != "display_all") {
        $competenceList = recherche($competenceList, json_decode($_POST['data'], true));
    }
        
}
*/

foreach ($competenceList as $value) :
    if (isset($value)) {

?>
            <div class="clickable_item flex_row flex_double_center">
                <input type='checkbox' class="inactive" onchange='competenceInputChange(<?= json_encode($value) ?>, <?= ($_SERVER["PHP_SELF"] == "/educateur/ajax/tableauActiviteDuMoment.php") ? "false" : "true" ?>);' id='CES_<?= $value['idCompetence'] ?>' <?php if(!in_array($value, $competenceList)) echo "checked"; else echo ""; ?>>
                <label class="flex_row flex_double_center flex_wrap" for='CES_<?= $value['idCompetence'] ?>'> <span> <?= $value['nom'] ?> </span> </label>
            </div>
<?php
    }
endforeach;
?>