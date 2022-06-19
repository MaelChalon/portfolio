-- question 1
select idModule, count(idSeance)
from Seances
group by idModule;

-- question 2
select idModule, count(idSeance)
from Seances
group by idModule
having count(idSeance)>80
order by count(idSeance) desc;

-- question 3
select idModule, count(distinct(idProf)) nb_prof
from Seances
group by idModule
order by count(distinct(idProf)) desc;

-- question 4
-- non car idProf n'est pas present

-- question 5
select jour, count(idSeance) nb_seances
from Seances
where monthname(jour)='september'
group by jour;

-- question 6
select idProf, monthname(jour) mois, count(idSeance)
from Seances
where idProf is not NULL
group by 1,2;

-- question 7
select left(idSalle,3) type_salle, sum(effectifMax) nb_places
from Salles
group by 1;

-- question 8
select idProf, group_concat(distinct(idGroupe))
from Seances
group by idProf
having idProf is not NULL;

-- question 9 
select idProf, sec_to_time(avg(time_to_sec(timediff(heureFin, heureDebut)))) durée_moyenne
from Seances
group by idProf
having idProf is not NULL
order by idProf;

-- question 10
select idProf,idGroupe, round(sum(time_to_sec(timediff(heureFin,heureDebut)))/3600,1)
from Seances
group by idProf, idGroupe
having idProf is not NULL and idGroupe is not NULL;

-- question 11
select right(left(idSalle,3),1), sum(effectifMax)
from Salles
where idSalle like 'IN%'
group by left(idSalle,3);