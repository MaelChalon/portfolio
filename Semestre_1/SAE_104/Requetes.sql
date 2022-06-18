USE p2102597 ;
SET default_storage_engine= InnoDB;
SET SQL_SAFE_UPDATES=0;
SET SQL_MODE='TRADITIONAL';

-- requete export des éléves d'une scéance
select num_etu , nom , prenom , nom_établissement , module_composante , mail_univ , mail_perso , num_tel , n.note, n.commentaire, count(justifier) nb_absence_injustifiée
from type_utilisateur tu join utilisateur u on tu.id_type=u.id_type
join participer p on u.id_utilisateur=p.id_utilisateur 
join composante c on u.id_composante=c.id_composante
left join noter n on n.id_utilisateur=u.id_utilisateur
left join (select  * 
			from etre_absent
            where justifier = false
            group by id_utilisateur) ea on ea.id_utilisateur=u.id_utilisateur
where tu.nom_type="etudiant" and p.id_sceance=1
group by u.id_utilisateur;

-- requete export de l'ensemble des étudiants
select u.num_etu , u.nom , u.prenom , nom_établissement , module_composante , u.mail_univ , u.num_tel , nom_sport , prof.nom , jour , concat( heure_de_debut, "-", heure_de_fin) heure , n.note, n.commentaire, count(justifier) nb_absence_injustifiée
from type_utilisateur tu join utilisateur u on tu.id_type=u.id_type
join participer p on u.id_utilisateur=p.id_utilisateur 
join composante c on u.id_composante=c.id_composante 
join scéance s on p.id_sceance=s.id_sceance
join sport on sport.id_sport=s.id_sport
join utilisateur prof on s.id_prof=prof.id_utilisateur
left join noter n on n.id_utilisateur=u.id_utilisateur
left join (select  * 
			from etre_absent
            where justifier = false
            group by id_utilisateur) ea on ea.id_utilisateur=u.id_utilisateur
where tu.nom_type="etudiant"
group by u.id_utilisateur
order by module_composante;


-- afficher tous les messages reçut d'un utilisateur
select id_message, objet, texte 
from messagerie
join utilisateur u on u.id_utilisateur=id_receveur
where u.nom = "onze";

-- afficher tous les messages envoyé par un utilisateur

select id_message, objet, texte 
from messagerie
join utilisateur u on u.id_utilisateur=id_envoyeur
where u.nom = "onze";

-- afficher l'ensemble des droits des administrateurs

select id_utilisateur, nom, prenom, gestion_base, gestion_base_vider_messagerie, gestion_base_ufr, gestion_base_raz_export, gestion_utilisateur, gestion_utilisateur_add_edit, gestion_sceances, gestion_sceances_add_edit
from utilisateur
natural join type_utilisateur tu
where tu.nom_type="administrateur";

-- afficher l'ensemble des droits d'un administrateur

select id_utilisateur, nom, prenom, gestion_base, gestion_base_vider_messagerie, gestion_base_ufr, gestion_base_raz_export, gestion_utilisateur, gestion_utilisateur_add_edit, gestion_sceances, gestion_sceances_add_edit
from utilisateur
natural join type_utilisateur tu
where nom = "admin2";

-- modifier les droits des administrateurs

update utilisateur
set gestion_base_ufr=true 
where nom="admin2";

-- afficher l'ensembles des composantes

select nom_établissement, module_composante
from composante
natural join etablissement
order by nom_établissement ;

-- afficher toutes les scéances d'un prof

select id_sceance, jour, heure_de_debut, heure_de_fin, place, u.nom, u.prenom, s.nom_sport
from scéance se
join utilisateur u on u.id_utilisateur=se.id_prof
join sport s on s.id_sport=se.id_sport
where u.nom = "prof1";

