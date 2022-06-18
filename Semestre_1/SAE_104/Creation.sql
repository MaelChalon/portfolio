USE p2102597 ;
SET default_storage_engine= InnoDB;
SET SQL_SAFE_UPDATES=0;
SET SQL_MODE='TRADITIONAL';

drop table if exists noter;
drop table if exists etre_absent;
drop table if exists participer;
drop table if exists Quota;
drop table if exists messagerie;
drop table if exists scéance;
drop table if exists utilisateur;
drop table if exists composante;
drop table if exists etablissement;
drop table if exists Campagne;
drop table if exists type_utilisateur;
drop table if exists sport;


CREATE TABLE Campagne(
   id_campagne INT auto_increment,
   date_debut DATE NOT NULL,
   date_fin DATE NOT NULL,
   PRIMARY KEY(id_campagne)
);

CREATE TABLE sport(
   id_sport INT auto_increment,
   nom_sport VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_sport)
);

CREATE TABLE type_utilisateur(
   id_type INT auto_increment,
   nom_type VARCHAR(100) NOT NULL,
   PRIMARY KEY(id_type)
);

CREATE TABLE etablissement(
   nom_établissement VARCHAR(100),
   PRIMARY KEY(nom_établissement)
);

CREATE TABLE composante(
   id_composante INT auto_increment,
   nom_établissement VARCHAR(100) NOT NULL,
   module_composante VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_composante),
   FOREIGN KEY(nom_établissement) REFERENCES etablissement(nom_établissement)
);

CREATE TABLE utilisateur(
   id_utilisateur INT auto_increment,
   num_etu INT,
   mail_univ VARCHAR(250) NOT NULL,
   nom VARCHAR(150) NOT NULL,
   prenom VARCHAR(150) NOT NULL,
   mdp VARCHAR(50) NOT NULL,
   num_tel INT,
   mail_perso INT,
   note Bool default false,
   gestion_base Bool default false,
   gestion_base_vider_messagerie Bool default false,
   gestion_base_ufr Bool default false,
   gestion_base_raz_export Bool default false,
   gestion_utilisateur Bool default false,
   gestion_utilisateur_add_edit Bool default false,
   gestion_sceances Bool default false,
   gestion_sceances_campagne Bool default false,
   gestion_sceances_add_edit Bool default false,
   id_composante INT,
   id_type INT,
   PRIMARY KEY(id_utilisateur),
   FOREIGN KEY(id_composante) REFERENCES composante(id_composante),
   FOREIGN KEY(id_type) REFERENCES type_utilisateur(id_type)
);

CREATE TABLE scéance(
   id_sceance INT auto_increment,
   jour VARCHAR(50) NOT NULL,
   heure_de_debut TIME NOT NULL,
   heure_de_fin time NOT NULL,
   place INT NOT NULL,
   id_prof INT NOT NULL,
   id_sport INT NOT NULL,
   PRIMARY KEY(id_sceance),
   FOREIGN KEY(id_prof) REFERENCES utilisateur(id_utilisateur),
   FOREIGN KEY(id_sport) REFERENCES sport(id_sport)
);

CREATE TABLE messagerie(
   id_message INT auto_increment,
   objet varchar(100),
   texte TEXT NOT NULL,
   id_envoyeur INT NOT NULL,
   id_receveur INT NOT NULL,
   PRIMARY KEY(id_message),
   FOREIGN KEY(id_envoyeur) REFERENCES utilisateur(id_utilisateur),
   FOREIGN KEY(id_receveur) REFERENCES utilisateur(id_utilisateur)
);

CREATE TABLE Quota(
   id_quota INT auto_increment,
   nbr_Quota INT NOT NULL,
   nom_établissement VARCHAR(100) NOT NULL,
   id_sceance INT,
   PRIMARY KEY(id_quota),
   FOREIGN KEY(nom_établissement) REFERENCES etablissement(nom_établissement),
   FOREIGN KEY(id_sceance) REFERENCES scéance(id_sceance)
);

CREATE TABLE participer(
   id_sceance INT,
   id_utilisateur INT,
   PRIMARY KEY(id_sceance, id_utilisateur),
   FOREIGN KEY(id_sceance) REFERENCES scéance(id_sceance),
   FOREIGN KEY(id_utilisateur) REFERENCES utilisateur(id_utilisateur)
);

CREATE TABLE etre_absent(
   id_sceance INT,
   id_utilisateur INT,
   date_abs DATE,
   justifier Bool  default false,
   PRIMARY KEY(id_sceance, id_utilisateur, date_abs),
   FOREIGN KEY(id_sceance) REFERENCES scéance(id_sceance),
   FOREIGN KEY(id_utilisateur) REFERENCES utilisateur(id_utilisateur)
);

CREATE TABLE noter(
   id_sceance INT,
   id_utilisateur INT,
   note DECIMAL(15,2),
   commentaire VARCHAR(50),
   PRIMARY KEY(id_sceance, id_utilisateur),
   FOREIGN KEY(id_sceance) REFERENCES scéance(id_sceance),
   FOREIGN KEY(id_utilisateur) REFERENCES utilisateur(id_utilisateur)
);

