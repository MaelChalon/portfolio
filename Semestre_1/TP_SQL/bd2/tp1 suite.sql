use p2103455;
set default_storage_engine= InnoDB;
set SQL_SAFE_UPDATES=0;

drop table if exists Suite ;
drop table if exists Module ;
drop table if exists Enseignant ;
drop table if exists Etudiant ;

drop table if exists Louer;
drop table if exists Reserver;
drop table if exists Films;
drop table if exists Clients;

create table Clients
	(ID_Client int auto_increment,
    nom varchar(30),
    prenom varchar(30),
    soldeCompte float,
    tel varchar(10),
    adresse varchar(100),
    e_mail varchar(50),
    constraint PK_Clients primary key(ID_Client));
    
create table Films
	(ID_Film int auto_increment,
    TitreFilm varchar(100) not null,
    genre varchar(30),
    realisateur varchar(100),
    acteur1 varchar(100),
    acteur2 varchar(100),
    acteur3 varchar(100),
    nb_DVD int,
    prix_achat float,
    constraint PK_Films primary key(ID_Film));
    
create table Reserver
	(ID_Client int,
    ID_Film int,
    date_reservation date default now(),
    constraint PK_Reserver primary key(ID_Client,ID_Film),
    constraint FK_Reserver_Clients foreign key(ID_Client) references Clients(ID_Client) on delete cascade,
    constraint FK_Reserver_Films foreign key(ID_Film) references Films(ID_Film)on delete cascade);
    
    create table Louer 
		(ID_Client int,
        ID_Film int,
        date_debut date default now(),
        Livraison bool,
        date_retour date,
        constraint PK_Louer primary key(ID_Client,ID_Film),
        constraint FK_Louer_Clients foreign key(ID_client) references Clients(ID_Client) on delete cascade,
        constraint FK_Louer_Films foreign key(ID_Film) references Films(ID_Film) on delete cascade);
        
insert into Films
values (default, "Affiche Alien - Le 8ème Passager","horreur","Ridley Scott","Sigourney Weaver","Tom Skerritt","Veronica Cartwright",50,10.55);
insert into Films
values (default, "La Soupe aux choux","comedie","Jean Girault","Louis de Funès","Jean Carmet","Jacques Villeret",30,9.30);

insert into Clients
values (default,"Pierre","Guy",15.02,"0101010101","150 rue de la republique Lyon","salutsalut@gmail.com");
insert into Clients
values (default,"Richard","Jeean",30.20,"0202020202","10 rue de la danse Saint didier sur je sais pas ou","jaimelasalade@gmil.com");

insert into Reserver
values (1,2,default);
insert into Reserver
values (2,1,default);

insert into Louer
values (2,2,default,true,"2021-12-02");
insert into Louer
values (1,1,default,true,"2021-12-02");

alter table Films add nb_res int;
update Films
set nb_res = (select count(ID_Client)
				from Reserver
                group by ID_Film
                having Films.ID_Film = Reserver.ID_Film);
                
delete from Films where upper(genre) = "HORREUR";

alter table Clients add NBLiv int;
update Clients
set NBLiv = (select count(livraison)
			from Louer 
            where Livraison = True
            group by ID_Client
            having Clients.ID_Client = Louer.ID_Client);     