function setIME(id) {
    let labels = document.querySelectorAll('label');

    labels.forEach(label => {

       if(label.htmlFor.startsWith('AE_')) {
           console.log(label.htmlFor);
           label.classList.remove('checkbox_element_style_1');
       }
    });
    document.querySelector(`label[for='AE_${id}']`).classList.add('checkbox_checked_element_style_1');
    document.getElementById('input_ime_list').value = id;
}
function setIdEducateur(idEducateur){
    document.getElementById("idEducateur").value = idEducateur;
}

function fill_fields(modify = true, id = null, nom = null, prenom = null, ime = null, type = null) {
    let input_id = document.getElementById("input_id_to_modify");
    let input_nom = document.getElementById("nom_educateur");
    let input_prenom = document.getElementById("prenom_educateur");
    let input_liste_ime = document.getElementById("recherche_ime_div");
    let input_type = document.getElementById("type");
    let input_is_modify = document.getElementById("isModify");
    let ajout_educateur_title = document.getElementById("ajout_educateur_title");
    let valider_ajout_educateur = document.getElementById("valider_ajout_educateur");
    let regeneratePassword = document.getElementById("regeneratePassword");
    let input = document.getElementById("isGeneratePassword");

    input.value = "1";
    modify ? regeneratePassword.classList.remove('inactive') : regeneratePassword.classList.add('inactive');

    ajout_educateur_title.textContent = !modify ? "Création d'une personne" : "Modification d'une personne";
    valider_ajout_educateur.textContent = !modify ? "Créer" : "Modifier";

    modify ? input_is_modify.value = "1" : input_is_modify.value = "0";
    id !== null ? input_id.value = id : input_id.value = "-1";
    nom !== null ? input_nom.value = nom : input_nom.value = "";
    prenom !== null ? input_prenom.value = prenom : input_prenom.value = "";
    ime !== null ? input_liste_ime.value = ime : input_liste_ime.value = "";
    type !== null ? input_type.value = type : input_type.value = "";
}