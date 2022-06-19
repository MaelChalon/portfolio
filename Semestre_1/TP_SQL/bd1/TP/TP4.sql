-- exercice 1
-- 1) 6421450
select count(*) from Seances, Utilise;

-- 2) 
select * from Seances s , Utilise e
where s.idSeance=e.idSeance;

-- 3) 
select s.idSeance, s.titreSeance, s.idModule, p.nomProf, 
p.prenomProf, s.idGroupe, s.jour, s.heureDebut, s.heureFin
from Seances s, Professeurs p
where s.idProf is not null and s.idProf = p.idProf;

-- exercice 2
-- 1) 
select s.idSeance, s.titreSeance, s.idModule, p.nomProf, 
p.prenomProf, s.idGroupe, s.jour, s.heureDebut, s.heureFin
from Seances s
join Professeurs p on s.idProf=p.idProf;

-- 2)
select  s.idSeance, s.titreSeance, s.idModule,
 s.idProf, s.idGroupe, g.effectif, s.jour, s.heureDebut, s.heureFin
from Seances s
join Groupes g on s.idGroupe = g.idGroupe;

select  s.idSeance, s.titreSeance, s.idModule,
 s.idProf, s.idGroupe, g.effectif, s.jour, s.heureDebut, s.heureFin
from Seances s natural join Groupes g;

-- 3)
select s.idSeance, s.titreSeance, s.idModule, s.idProf, s.idGroupe,
s.jour, s.heureDebut, s.heureFin, group_concat(u.idSalle)
from Seances s
join Utilise u on s.idSeance = u.idSeance
group by 1;

select s.idSeance, s.titreSeance, s.idModule, s.idProf, s.idGroupe,
s.jour, s.heureDebut, s.heureFin, group_concat(u.idSalle)
from Seances s natural join Utilise u
group by 1;

-- 4) 
select * 
from Seances se
join Utilise u on se.idSeance = u.idSeance
join Salles sa on u.idSalle = sa.idSalle
join Groupes g on se.idGroupe = g.idGroupe;

-- a) 
select count(*) from Seances,Utilise,Salles,Groupes;
-- 5 907 734 000 lignes
-- b) c'est beaucoup trop pour l'ordinateur

-- 5)
select u.idSeance,sum(sa.effectifMax), g.effectif
from Utilise u 
join Seances se on u.idSeance = se.idSeance
join Groupes g on se.idGroupe = g.idGroupe
join Salles sa on u.idSalle = sa.idSalle
group by idSeance
having g.effectif > sum(sa.effectifMax);

-- ex 3
-- 1)
-- a)
select oeuvre, musee, nompeintre, prenompeintre, nbvisiteur
from expo join artiste
on expo.peintre=artiste.nompeintre;

-- b)
-- les deux colonnes n'ont pas le même nom




