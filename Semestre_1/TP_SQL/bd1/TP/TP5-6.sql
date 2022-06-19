-- ex 1
-- 1)
select idSeance
from Seances
where idModule = (select idModule
					from Modules 
                    where nomModule='Mathématiques discrètes');

-- 2)
select * 
from Seances
where month(jour)=9 and idprof = (select idProf
											from Seances where idSeance=17);
                                            
-- 3)
select idSalle
from Salles
where effectifMax > (select effectifMax from Salles where idSalle='Foyer RPF');

-- 4)
select idModule
from Modules
where (CM+TD+TP)> (select avg(CM+TD+TP)
					from Modules);
                    
-- 5)
select idSeance
from Utilise
group by idSeance
having count(idSalle) > (select count(idSalle)
						from Utilise where idSeance=1184);

-- ex 2
-- 1)
select distinct(idSalle) 
from Utilise
where idSeance = any (select idSeance from Seances where month(jour)=6);

-- 2)
select *
from Seances
where idGroupe in (select idGroupe from Groupes where effectif>25);

--  3)
select distinct(idGroupe)
from Seances
where idGroupe not in (select idGroupe
						from Seances where idProf = 'JFA');

-- 4)
select *
from Seances
where idSeance not in ( select idSeance from Utilise);

-- 5)
select distinct(idProf) idProf, 'a cour le meme jour que LMI le', jour
from Seances
where idProf is not NULL and idProf <> 'LMI' and
		jour in (select jour
				from Seances 
                where idProf = 'LMI' 
                and month(jour)=11);

-- 6) 
select distinct(idProf)
from Seances S1
where idProf not in (select distinct(idProf)
					from Seances where idGroupe not like 'S4G3%' and idProf is not NULL);

-- 7)
select distinct(idSalle)
from Utilise
where idSalle not in (	select distinct(idSalle)
						from Utilise
						where idSeance in (	select idSeance 
											from Seances
											where idProf = 'JFA'));

-- 8)

select *
from Seances
where idModule='M1102' and jour > all (	select max(jour)
										from Seances
										where idModule='M1101'
                                        group by idModule); 
