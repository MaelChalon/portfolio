-- exercice 1

create or replace view departement 
as select departement, count(idVille) nbVille, sum(superficie) superficie, max(altitudeMax) altitudeMax, min(altitudeMin) altitudeMin, sum(pop2010) pop2010
from films.villes
group by departement;

select * from departement where superficie = (select max(superficie) from departement);

-- exercice 2

create or replace view EMP_NOTTHRIL
as select emp.idemprunteur, emp.nom, emp.prenom, emp.dateNaissance, emp.ville from films.emprunteurs emp
where emp.idemprunteur not in (select distinct(emp.idemprunteur) from films.emprunteurs emp
								natural join films.emprunts em
								natural join films.exemplaires ex
								natural join films.films f
                                where f.categorie = 'thriller');

select v.nom from EMP_NOTTHRIL nott
join films.villes v on nott.ville = v.idVille
group by ville
having count(distinct(nott.idemprunteur)) > 0;

select v.nom from EMP_NOTTHRIL nott
join films.villes v on nott.ville = v.idVille
group by ville
having count(distinct(nott.idemprunteur)) > 1;

-- exercice 3 

create or replace view EMPCAT 
as select emp.idemprunteur idemprunteur, f.categorie, count(f.categorie) "nombre d'emprunt"
from films.emprunteurs emp
natural join films.emprunts em
natural join films.exemplaires ex
natural join films.films f
group by emp.idemprunteur, f.categorie;

select sum(3) "nombre d'emprunt total"
from EMPCAT
group by idemprunteur;

-- exercice 4

create or replace view Emp_Bourg as
select emp.idemprunteur, emp.nom, emp.prenom, emp.dateNaissance from films.emprunteurs emp
join films.villes v on emp.ville = v.idVille
where v.nom = 'Bourg-en-Bresse';

select * from Emp_Bourg;