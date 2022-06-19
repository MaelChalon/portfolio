-- 1

select * from Seances
where idProf ="ADA" and jour = "2020-11-24";

select * from Seances 
where idProf = "ADA"
intersect
select * from Seances
where  jour = "2020-11-24";


-- 2

select idGroupe from Seances
where idGroupe like "S1%"
intersect
select idGroupe from Seances
where idProf = "APE" or idProf = "EDU";

select distinct(idGroupe) from Seances
where idGroupe like "S1%" and (idProf = "APE" or idProf = "EDU");

-- 3

select idGroupe from Seances
where idGroupe like "S1%"
intersect
select idGroupe from (	select idGroupe from Seances where idProf = "EDU"
						intersect
                        select idGroupe from Seances where idProf = "APE")a;

-- 4

select distinct (idGroupe) from Groupes
except 
select idGroupe from Seances
where month(jour)=12 and heureDebut="08:30:00";

-- 5
-- tous les groupes ayant eu un cours en rapport avec l'anglais et un cour le 24 novembre 2020

-- 6
-- tous les sceances ayant un rapport avec l'anglais et un cour le 24 novembre 2020

-- 7
select distinct(idGroupe) from (select * from Seances where JOUR='2020-11-24'
intersect
select * from Seances where lower(titreSeance) like '%anglais%')a;

-- 8 
select idGroupe from Seances
where JOUR='2020-11-24' and lower(titreSeance) like '%anglais%';

-- 9 
select idSalle from Salles
except
select distinct(idSalle)
from Seances s join Utilise u on s.idSeance = u.idSeance
where month(jour)=12;

-- 10
select distinct(idSalle)
from Seances s join Utilise u on s.idSeance = u.idSeance
where idProf ="ADA"
except 
select distinct(idSalle)
from Utilise u join Seances s on u.idSeance = s.idSeance
where idProf <> "ADA";

-- 11
-- a)
select idGroupe
from Seances
where idGroupe like "S_G_._" and jour<>'2021-03-04';
-- les demi groupes des sceances qui ne sont pas le 4 mars
-- b)
select idGroupe 
from Groupes
where idGroupe like "S_G_._"
except 
select distinct(idGroupe)
from Seances
where jour='2021-03-04';

select idGroupe 
from Groupes
where idGroupe like "S_G_._" and idGroupe not in(	select distinct(idGroupe)
													from Seances
													where jour='2021-03-04' and idGroupe is not null);


-- 12) les demi grouipe qui n'ont pas eu cours ni le 03/04/2021 ni le 04/04/2021

-- 13)
select distinct idGroupe
from Seances
where idGroupe like "S_G_._" and idGroupe not in
(select idGroupe from Seances where jour='2021-03-04')
union 
select distinct idGroupe
from Seances
where idGroupe like "S_G_._" and idGroupe not in
(select idGroupe from Seances where jour='2021-04-04');

-- 14)

select * 
from Salle s
join Seances se on s.idSeance = se.idSeance
where 