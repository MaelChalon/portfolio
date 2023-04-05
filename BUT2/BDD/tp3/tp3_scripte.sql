START Transaction; /* mode transactionnel*/
Insert into Product values (default,'Fast furious 1', 12, 200);
Select * from Product;
Rollback;
Select * from Product;
START Transaction; /* mode transactionnel*/
Insert into Product values (default, 'Fast furious 1', 12, 200);
Commit;
Select * from Product;

grant select on Product to p2104000;

INSERT INTO Product VALUES (0,'Fast & furious 2', 10,1500) ;

set autocommit=0;

select * from p2104000.Customer;

GRANT Select on Customer to p2104000;

select firstName from Customer where idCustomer = 1;

revoke select on Customer from p2104000;

select firstName from Customer where idCustomer = 1 for update;

GRANT update, select on Customer to p2104000;

START TRANSACTION;
update Customer set firstName = "bob" where idCustomer = 1; 
commit;
select * from Customer;

Revoke update on Customer FROM p2104000;


-- exercice 3

SET AUTOCOMMIT=0;
DROP TABLE IF EXISTS Employe ;
CREATE TABLE Employe (
nom varchar(20),
prenom varchar(20),
salaire float) ;
Delete from Employe;
SAVEPOINT P1 ;
INSERT INTO Employe VALUES('Perrin','Eric',2300);
INSERT INTO Employe VALUES('Perrin','Alfred',2400);
INSERT INTO Employe VALUES('Perrin','Brice',2500);
INSERT INTO Employe VALUES('Perrin','Jérome',2000);
SAVEPOINT P2 ;
INSERT INTO Employe VALUES('Duchateau','Emilie',1300);
INSERT INTO Employe VALUES('Duchateau','Bernard',1800);
INSERT INTO Employe VALUES('Duchateau','Eloise',1550);
INSERT INTO Employe VALUES('Duchateau','Lina',2000);
SAVEPOINT P3 ;
INSERT INTO Employe VALUES('Descharmes','Yanick',1800);
INSERT INTO Employe VALUES('Descharmes','Laura',1900);
INSERT INTO Employe VALUES('Descharmes','Charles',3550);
INSERT INTO Employe VALUES('Descharmes','Robin',2200);
-- Une seule action peut-être décommentée à la fois
-- ROLLBACK TO SAVEPOINT P1;
-- ROLLBACK TO SAVEPOINT P2;
-- ROLLBACK TO SAVEPOINT P3;

commit;

select * from Employe;




