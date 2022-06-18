USE p2102597 ;
SET default_storage_engine= InnoDB;
SET SQL_SAFE_UPDATES=0;
SET SQL_MODE='TRADITIONAL';

insert into etablissement
values("lyon1"),
	("lyon3");
    
insert into composante
values(default,"lyon1","informatique"),
	(default,"lyon3","histoire"),
    (default,"lyon1","GTE");

insert into type_utilisateur
values(default,"super administrateur"),
	(default,"administrateur"),
    (default,"etudiant"),
    (default,"profeseur");

insert into utilisateur (num_etu,mail_univ,nom,prenom,mdp,note,id_composante,id_type)
values(10,"10@lyon1","dix","9+1","10",true,1,3),
		(11,"11@lyon1","onze","10+1","11",true,1,3),
        (12,"12@lyon1","douze","11+1","12",true,3,3),
        (13,"13@lyon1","Treize","12+1","13",true,1,3),
        (14,"14@lyon1","Quatorze","13+1","14",true,3,3),
        (15,"15@lyon1","quinze","14+1","15",true,1,3),
        (16,"16@lyon3","seize","15+1","16",false,2,3),
        (17,"17@lyon1","dix-sept","16+1","17",true,1,3),
        (18,"18@lyon1","dix-huit","17+1","18",true,3,3),
        (19,"19@lyon3","dix-neuf","18+1","19",false,2,3),
        (20,"20@lyon3","vingt","19+1","20",false,2,3);

insert into utilisateur (mail_univ,nom,prenom,mdp,id_type)
values("prof1@lyon1","prof1","prof-1","prof1",4),
		("prof2@lyon1","prof2","prof-2","prof2",4),
        ("pro3@lyon1","prof3","prof-3","prof3",4);

insert into utilisateur (mail_univ,nom,prenom,mdp,id_type)
values("admin@lyon1","admin1","admin-1","admin1",2),
		("admin@lyon1","admin2","admin-2","admin2",2),
        ("superadmin@lyon1","superadmin","superadmin","superadmin",1);

insert into sport
values(default,"escalade"),
		(default,"tennis"),
        (default,"natation");

insert into scéance
values	(default,"Lundi",'18:15','20:15',15,12,1),
		(default,"Mercredi",'19:00','20:00',10,14,3),
        (default,"Jeudi",'13:30','15:00',10,13,2),
        (default,"Mardi",'18:20','20:20',20,12,1);

insert into participer
values	(3,1),
		(3,2),
		(3,3),
        (2,4),
        (2,5),
        (2,6),
        (1,7),
        (1,8),
        (1,9),
        (4,10);

insert into etre_absent
values	(1,8,now(),true),
		(1,8,'2021-04-01',default),
		(3,2,'2021-04-01',default);
	
insert into noter
values (1,9,15,"c'est bien");

insert into Campagne
values	(default,'2021-09-01','2021-09-20');

insert into Quota
values	(default,5,"lyon1",1),
		(default,5,"lyon3",1);

insert into messagerie
values (default,"il faut que je te parle","Luke, je suis ton pere",1,2),
		(default,"one piece","le roi des pirates se seras moi !!!!!",2,1);