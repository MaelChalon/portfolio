-- 1)
select * from Seances
where idProf=(select idProf from Seances
				where idSeance = 17);
                
-- 2)
select * from Modules
where CM+TD+TP > (select avg(CM+TD+TP) from Modules);

-- 3)
select distinct(idSalle) from Utilise
where idSeance in (select idSeance from Seances 
					where month(jour)=6);
                    
-- 4)
select * from Groupes
where idGroupe NOT in (select distinct(idGroupe) from Seances
						where idProf = "JFA");
                        
-- 5)
-- returne toute les sceances qui posséde le meme nom mais pas le meme prof.

-- 6)
select distinct(s1.jour) from Seances s1 where not exists (select * from Seances s2
													where s2.jour = s1.jour+1);

-- 7)
select distinct(idProf), titreSeance  from Seances s1
where exists (select * from Seances s2
				where s1.idProf = s2.idProf and s1.titreSeance = s2.titreSeance
                and s1.idGroupe<>s2.idGroupe);
                
                
-- 8)
select distinct(idProf) from Seances s1
where idProf is not null and not exists (select * from Seances s2
				where s1.jour = s2.jour and s1.idProf<>s2.idProf);
                
-- 9)
select idProf from Professeurs
except 
select distinct(idProf) from Seances 
where jour in (select jour from Seances
				where idProf = "LMI");
                
-- 10)

select distinct(idModule) from Seances s1
where titreSeance like "%TP noté%";

select distinct(idModule) from Seances s1
where titreSeance like "%TP noté%" and not exists (select * from Seances s2
												where s1.idModule = s2.idModule and s1.jour < s2.jour);
