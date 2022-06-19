-- question 1
select prenomProf
from Professeurs
where prenomProf = 'Lionel' or prenomProf = upper('lionel');

-- question 2
select idmodule, left(nomModule,20) nomPartiel
from Modules;

-- question 3
select idModule, replace(nomModule,'à','a') nvNomModule
from Modules;

-- question 4
select idModule, concat('CM(',CM,') TD(',TD,') TP(',TP,')') recap
from Modules
order by idModule asc;

-- question 5
select idProf, nomProf,PrenomProf, concat(substr(idProf,1,2),lower(substr(idProf,3,3))) trigramme
from Professeurs;

-- question 6
select idModule, substr(nomModule,1,locate(' ', nomModule,1)) debut_nom
from Modules;

-- question 7
select titreSeance
from Seances
where dayname(jour) = 'friday' and idProf = 'JBO';

select titreSeance
from Seances
where weekday(jour) = '4' and idProf = 'JBO';

-- question 8
select titreSeance
from Seances
where hour(heureFin)>=18;

-- question 9
select idSeance, jour, heureDebut, heureFin, timediff(heureFin, heureDebut) durée
from Seances
where idProf = 'JBO'
order by jour asc, heureDebut asc;

-- question 10
select titreSeance
from Seances
where time_to_sec(timediff(heureFin,heureDebut))<45*60;

-- question 11
select *
from Seances
where idProf is not null
order by timediff(heureFin,heureDebut)desc, idProf asc;
order by timediff(heureFin,heureDebut)desc, idProf asc;