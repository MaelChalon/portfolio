<?php
/** @var Database $db */
$listeIME = $db->getIMEs();
if(isset($_POST['search_word']))
    if($_POST['search_word'] != '')
        $listeIME = recherche($listeIME, $_POST['search_word']);
foreach ($listeIME as $IME) :
    ?>
    <div class="clickable_item flex_row flex_double_center">
        <input type='radio' onchange='setIME(<?= $IME['idIME'] ?>)' id='AE_<?= $IME['idIME'] ?>' name="listIME[]" class='inactive'>
        <label class="label_for_input_CATS flex_row flex_double_center flex_wrap" for='AE_<?= $IME['idIME'] ?>'> <span> <?= $IME['nom'] ?> </span> </label>
    </div>
<?php
endforeach;