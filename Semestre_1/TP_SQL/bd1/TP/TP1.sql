-- EXERCICE 1:

-- question 1:
select *
FROM Seance;
-- il y a 2450 lignes

-- question 2:
-- car idseance est la cle primaire de la table seance

-- question 3 
select *
from Utilise;
-- la table possede 2621 lignes

-- question 4
-- car la cle primaire est formee des 2 attributs de la tables

-- question 5
-- il n'est pas possible d'avoir ces deux attributs car dans le
-- premier cas idprof a pris pour valeur BERGER et non ABE et
-- dans le second cas idprof a pris pour valeur ABe or le e doit 
-- etre en majuscule. idprof doit absolument avoir la meme 
-- valeur que dans la table Professeur.alter

-- EXERCICE 2

-- question 1
select *
from Groupes;

-- question 2
select idGroupe nom, effectif, parent 'est sous-groupe de'
from Groupes;

-- qustiion 3
-- pour la table il est important de respecter la casse 
-- par contre pour les attributs elle n'est pas importante

-- question 4
SELECT nomProf, prenomProf
FROM Professeurs;

-- question 5
SELECT nomProf, prenomProf
FROM Professeurs
ORDER BY nomProf desc;

-- question 6
SELECT distinct prenomProf
FROM Professeurs
ORDER BY prenomProf asc;
-- il faut rajouter distinct

-- question 7
SELECT nomModule, CM+TP+TD 'total des heures' 
FROM Modules;

-- question 8
select 'la séance', idSeance, 'a reservé la salle', idSalle
fROM Utilise
Order By idSeance asc;

-- question 9
select *
from Salles
order by effectifMax desc;

-- question 10
select nomModule, TP
from Modules
where TP>10
order by TP asc;

-- question 11
select nomModule, TP
from Modules
where TP>=15 and TP<= 20;

-- question 12 
select * 
from Seances
where jour < '2021-02-10' and jour < '2021-05-18'
order by jour asc;

-- question 13
select idSeance, jour, heureDebut, heureFin 
from Seances
where (jour between '2021-01-01' and '2021-01-31' and heureFin <='12:30') or (jour between '2021-03-01' and '2021-03-31'and heureFin <='12:30');

-- question 14
select titreSeance, idProf
from Seances
where idProf is null;

-- question 15
select distinct(idModule)
from Seances
where idProf is null;

-- question 16
select titreSeance
from Seances
where idProf is not null;

-- question 17
select nomModule
from Modules
where nomModule like '%donnée%';

-- question 18
select nomModule
from Modules
where nomModule like '_a%';

-- question 19
select titreSeance
from Seances
where idGroupe like 'S1%';

-- question 20
select idmodule, nomModule, CM, TD, TP
from Modules
where TD < TP;
