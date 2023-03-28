<?php
if (isset($_POST['data'])) {
    $listCategories = json_decode($_POST['data']);
    foreach ($listCategories as $cat) :
?>
        <div class='NALCATID_<?= $cat->idCategorie ?> flex_row space_between list_item_style_2'>
            <p class='NALCATID_<?= $cat->idCategorie ?>'><?= $cat->nom ?></p>
            <a href='#' onclick='removeCategorie(<?= json_encode($cat) ?>)' class='NALCATID_<?= $cat->idCategorie ?>'><span class="close_window"></span></a>
            <input type="hidden" id='NCAT_<?= $cat->idCategorie ?>' name="idCategories[]" value="<?= $cat->idCategorie ?>">
        </div>
<?php
    endforeach;
}
?>