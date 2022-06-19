use p2103455;
set default_storage_engine= InnoDB;
set SQL_SAFE_UPDATES=0;

drop table if exists Louer;
drop table if exists Reserver;
drop table if exists Films;
drop table if exists Clients;

-- question 2
drop table if exists Suite ;
drop table if exists Module ;
drop table if exists Enseignant ;
drop table if exists Etudiant ;


create table Etudiant 
	(NumEtudiant int,
	Nom varchar(30),
	Prenom varchar(30),
	DateNaiss timestamp,
	DateInscription timestamp,
	Tel varchar(10),
	Adresse varchar(254),
	constraint PK_etudiant primary key(NumEtudiant));

create table Enseignant 
	(NumEnseignant int,
	Nom varchar(30),
	Prenom varchar(30),
	NumBureau varchar(6),
	constraint PK_Enseignant primary KEY(NumEnseignant));

create table Module 
	(CodeModule varchar(6),
	NumEnseignant int,
	NomModule varchar(50),
	VolumeHoraire float,
	constraint PK_Module primary KEY(CodeModule),
	constraint FK_Module_Enseignant foreign key(NumEnseignant) references Enseignant(NumEnseignant) on delete set Null);

create table Suite 
	(NumEtudiant int,
	Module varchar(6),
	note float,
	constraint PK_Suite primary key(NumEtudiant,Module),
	constraint FK_Suite_Etudiant foreign key(NumEtudiant) references Etudiant(NumEtudiant),
	constraint FK_Suite_Module foreign key(Module) references Module(CodeModule));

-- question 4

insert into Etudiant
values (1,'bombard','alexandre','2000/02/05',null,null, null);
insert into Etudiant
values (2,'menez','jeremy','2001/04/03',null,null, null);
insert into Etudiant
values (3,'einstein','besem','2002/05/05',null,null, null);
-- il manquait la colone tel a affecter

insert into Enseignant
values (1,'lagraa','Hamida','E205');
insert into Enseignant
values (2,'enffantin','Brice','E209');
insert into Enseignant
values (3,'buathier','lionel','E301');

insert into Module
values ('M1102',1,'introduction aux base de donnees',24);
insert into Module
values ('M1104',2,'Algorithmique',30);
insert into Module
values ('M2103',3,'Java',20);
-- l'enseingant 8 n'existais pas 

insert into Suite
values(1,'M1102',12.0);
insert into Suite
values(2,'M1102',8.0);
insert into Suite
values(3,'M1104',9.0);
insert into Suite
values(1,'M2103',18.0);
insert into Suite
values(3,'M2103',15.0);
-- le module 'M2105' et l'etudiant 5 n'existais pas

-- question 6

select e.Nom
from Suite s
natural join Etudiant e
join Module m on m.CodeModule=s.Module
where m.NomModule='Java';

-- question 7

select distinct(e.Nom)
from Suite s
join Module m on m.CodeModule=s.Module
join Etudiant e on e.NumEtudiant = s.NumEtudiant
join Enseignant es on es.NumEnseignant = m.NumEnseignant
where es.NumEnseignant=3;

-- question 8

insert into Module
values ("M2104",3,"IHM",20);

-- question 9
insert into Suite
values (2,"M2104",12);
insert into Suite
values (3,"M2104",8);

-- question 10
drop table if exists Module_Buathier;

create table Module_Buathier 
as select CodeModule, NomModule 
from Module
where NumEnseignant=3;

-- question 11

drop table if exists Etudiant_Buathier;
create table Etudiant_Buathier
as select distinct(e.Nom)
from Suite s
join Module m on m.CodeModule=s.Module
join Etudiant e on e.NumEtudiant = s.NumEtudiant
join Enseignant es on es.NumEnseignant = m.NumEnseignant
where es.NumEnseignant=3;

-- question 12
alter table Module add NB_Etudiants int;
update Module
set NB_Etudiants = (select count(NumEtudiant)
					from Suite
                    group by Suite.module
                    having Suite.Module = Module.CodeModule);
describe Module;

-- question 13

alter table Enseignant add NB_Module int;

update Enseignant
set NB_Module = (select count(CodeModule)
				from Module
                group by NumEnseignant
                having Enseignant.NumEnseignant = Module.NumEnseignant);
                
-- question 14

-- supprimer la premier ligne est impossible car elle sert de clef etranger pour d'autre table

delete from Enseignant
where NumEnseignant = 1;

