<section id="ajouter_competence_existante" class="flex_column flex_double_center inactive popup_init popup_style_1">
    <div class="flex_row popup_title">
        <h3>Rechercher une compétence</h3>
        <a href="#" id="quit_popup_competence_existante" onclick="popNoActive('ajouter_competence_existante')"><span class="close_window"></span></a>
    </div>
    <div class="flex_column flex_double_center popup_content">
        <label for="recherche_competence">Nom d'une compétence :</label>
        <div class="search_zone_style_1">
            <img src="/img/magnifying-glass.png" alt="loupe" class="loupe">
            <input type="text" name="nom_competence" class="searchbar_style_1" id="recherche_competence_existante" placeholder="Rechercher"> <!-- onkeyup="updateRechercheCompExistantes()"> -->
        </div>
    </div>
    <div id="liste_competences_existantes" class="output list popup_content scrollbar_style_1 scroll_div_element scroll_div_large">
        <?php include_once __DIR__."/../ajax/listeCompetencesExistantes.php"; ?>
    </div>
    <div id="addCompCE"></div>
    <a id="valider_competences_existantes" href='#' class="button_style_2" onclick='popNoActive("ajouter_competence_existante"); <?php if($_SERVER["PHP_SELF"] == "/index.php/educateur/ajax/tableauActiviteDuMoment") echo "sendAjaxModifyCompetences();";
                                                                                                                                        else if($_SERVER["PHP_SELF"] == "/index.php/educateur/categories") echo "sendCategorieCompetences()"; else echo ""; ?>'>Valider</a>
</section>
<!--
<script src="/educateur/javascript/nouvelle_activite.js"></script>
<script> updateRechercheCompExistantes(true) </script>
-->