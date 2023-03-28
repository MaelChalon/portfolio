var ActiviteSearchString = "";

function rechercher_activite(str) {
    console.log("rechercher_activite");
    ActiviteSearchString = str;
    $.ajax({
        type: "POST",
        url: "/educateur/ajax/suggestactivite",
        data: {
            q: str,
        },
        success: function (response) {
            $("#outputactivite").html(response);
        }
    });
}

function choose_activite(activite) {
    console.log("choose_activite");
    $.ajax({
        type: "POST",
        url: "/SESSION",
        data: {
            type: "choose",
            personne: activite,
            name_session: "activite_selected"
        },
        success: function (response) {
            // Appeler la fonction AJAX pour rafraîchir la page
            rechercher_activite(ActiviteSearchString);

        }
    });
}

function update_activite() {
    console.log("update_activite");
    $.ajax({
        type: "POST",
        url: "/educateur/ajax/Listeactivite",
        success: function (response) {
            console.log(response);
            $("#activite").html(response);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus + ": " + errorThrown);
            alert("Une erreur est survenue lors de la requête AJAX.");
        }
    });
}