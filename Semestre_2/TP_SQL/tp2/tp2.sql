-- 1
-- a)
select idSeance from Seances 
where idSeance not in (select idSeance from Utilise);

-- b)
select *
from Seances s left outer join Utilise u on s.idSeance = u.idSeance
where u.idSalle is Null;

-- 2
select *
from Seances s left outer join Utilise u on s.idSeance = u.idSeance
where u.idSalle is Null or u.idSalle = "IN0.09";

-- 3
select *
from Salles s left outer join Utilise u on s.idSalle = u.idSalle
where u.idSeance is Null;

select * from Salles
where idSalle not in (select idSalle from Utilise);

select idSalle from Salles
except
select idSalle from Utilise;

-- 4
select s.idSalle, count(u.idSeance) nb_utilise
from Salles s left outer join Utilise u on s.idSalle = u.idSalle
group by s.idSalle
order by nb_utilise asc;

-- 5
select * 
from Groupes g left outer join Seances s on g.idGroupe = s.idGroupe
where s.idSeance is Null;

select idGroupe from Groupes
except
select idGroupe from Seances;

-- 6
select g.idGroupe, sec_to_time(sum(ifnull(heureFin-heureDebut,0))) total_heure 
from Groupes g left outer join Seances s on g.idGroupe = s.idGroupe
group by g.idGroupe
order by total_heure asc;

-- 7)
select p.idProf, count(s.idSeance) nb_Seances 
from Professeurs p join Seances s on p.idProf = s.idProf
where month(s.jour)=6
group by p.idProf;