<?php
/** @var Database $db */
$result = $db->getCategories($Personne['idIMESelected']);
/*

Recherche
if(isset($_POST['data'])) {
    if(json_decode($_POST['data'], true) != "display_all") {
        $result = recherche($result, json_decode($_POST['data'], true));
    }
        
*/

foreach ($result as $value) :
?>
    <div class="clickable_item flex_row flex_double_center">
        <input type='checkbox' onchange='categorieInputChange(<?= json_encode($value) ?>)' id='CATS_<?= $value['idCategorie'] ?>' class='inactive'>
        <label class="label_for_input_CATS flex_row flex_double_center flex_wrap" for='CATS_<?= $value['idCategorie'] ?>'> <span> <?= $value['nom'] ?> </span> </label>
    </div>
<?php
endforeach;
?>