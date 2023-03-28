<?php
$ime = $Personne["idIMESelected"]; //faire la detection de l'ime grace à une drop box
/** @var Database $db */
$statement = $db->query("select idActivite,  nom, description, idIME from Activite where idIME = :idIME_;", array("idIME_" => $ime));
$listActivite = $statement->fetchAll();
?>
<!DOCTYPE html>
<html lang="fr">

<head>
    <?php include __DIR__ . "/../include/head.php" ?>
    <title>Activités</title>
</head>

<body class="scroll_div_element scrollbar_style_1">
    <?php include_once __DIR__ . "/../modules/header.php"; ?>
    <main id="page_educateur_activites">
        <script src="/jsEducateur/popUp.js"></script>
        <div id="addActivity">
            <a href="nouvelle_activite?resetSession=true" class="button_style_2">Créer une Activité</a>
        </div>
        <div class="collection">
            <?php
            foreach ($listActivite as $activite) :
            ?>
            <div class="collection_item_style_1 anim_hover_1">
                <a href="nouvelle_activite?idActivite=<?= $activite->idActivite ?>" class="card_style_1">
                    <h2><?= $activite->nom ?></h2>
                    <p><?= $activite->description ?></p>
                </a>
            </div>
            <?php endforeach ?>
        </div>
        <div id="ajax_activite_div"></div>
    </main>
    <script src="/jsEducateur/nouvelle_activite.js"></script>
    <?php
    include_once __DIR__ . "/../modules/footer.php";
    ?>