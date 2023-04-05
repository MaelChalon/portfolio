/*** Creation de la base de données */

SET default_storage_engine= InnoDB;
SET SQL_SAFE_UPDATES=0;

drop table if exists Journal;
drop table if exists Inventaire;
drop table if exists ProductEvolution;
drop table if exists InvoiceLine;
drop table if exists Invoice;
drop table if exists Customer;
drop table if exists Product;

/*==============================================================*/
/* Table : Customer                                             */
/*==============================================================*/
create table Customer
(
   idCustomer           int not null auto_increment,
   name                 varchar(25),
   firstName            varchar(25),
   adress               varchar(254),
   birthDate            datetime,
   phoneNumber          varchar(10),
   gender               char,
   primary key (idCustomer)
);

/*==============================================================*/
/* Table : Invoice                                              */
/*==============================================================*/
create table Invoice
(
   idInvoice            int not null auto_increment,
   idCustomer           int,
   DateEst              datetime,
   primary key (idInvoice),
   constraint FK_Invoice_Customer foreign key (idCustomer)
      references Customer (idCustomer) on delete restrict on update restrict
);

/*==============================================================*/
/* Table : Product                                              */
/*==============================================================*/
create table Product
(
   idProduct            int not null auto_increment,
   designation          varchar(254),
   unitPrice            float,
   stock                int,
   primary key (idProduct)
);

/*==============================================================*/
/* Table : InvoiceLine                                          */
/*==============================================================*/
create table InvoiceLine
(
   idInvoice            int,
   idProduct            int,
   quantity             int,
   primary key (idInvoice, idProduct),
   constraint FK_invoiveLine_Invoice foreign key (idInvoice)
      references Invoice (idInvoice) on delete restrict on update restrict,
   constraint FK_invoiveLine_product foreign key (idProduct)
      references Product (idProduct) on delete restrict on update restrict
);


/********************************** Partie 02 ****************************/

/*==============================================================*/
/* Question : 01                                                */
/*==============================================================*/

drop procedure if exists insertCustomer;


drop procedure if exists insertCustomer;
delimiter $
CREATE  PROCEDURE insertCustomer(IN name varchar(25),
   IN firstName            varchar(25),
   IN adress               varchar(254),
   IN birthDate            datetime,
   IN phoneNumber          varchar(10),
   IN gender               CHAR)
   begin
   INSERT INTO Customer VALUES (0, name, firstname, adress, birthDate, phoneNumber, gender);
   end $
delimiter ;
  

   
/*==============================================================*/
/* Question : 02                                                */
/*==============================================================*/ 
 
drop procedure if exists insertProduct;


delimiter $
CREATE  PROCEDURE insertProduct(IN designation varchar(254),
   IN unitPrice            float,
   IN stock                int)
   begin
   INSERT INTO Product VALUES (0, designation,unitPrice,stock);
   end $
delimiter ; 
   
/*==============================================================*/
/* Question : 03                                                */
/*==============================================================*/ 
   
call insertCustomer ('Coulomb','Francois','4, rue liberte','19800212','06456780','m');
call insertCustomer ('Dupont','Bernard','120, square zola','19720812','01345678','m');  
call insertCustomer ('Corda','Nathalie','66, bd napoleon III','19770210','06455790','f'); 
call insertCustomer ('Pierre','Alexandra','1, place des martyres','19720908','01645870','f'); 

call insertProduct ('DVD Matrix','23.5','100');
call insertProduct ('DVD The Island','49.30','100');
call insertProduct ('CD Album Yanni','25.9','100');
call insertProduct ('DVD Divergent','10.00','5');
call insertProduct ('DVD Allegiant','12.00','5');
call insertProduct ('DVD Insurgent','11.5','5');


drop procedure if exists buyProduct;
delimiter $
create procedure buyProduct (IN customer int,
IN product int,
IN qty int, 
IN last_invoice boolean)
BEGIN 
 declare v_invoice integer default 0; 
IF qty < (SELECT stock FROM Product where idProduct = product) then 
    IF last_invoice is false then 
		INSERT INTO Invoice (idCustomer,DateEst) VALUES	(customer,now());
        INSERT INTO InvoiceLine VALUES (last_insert_ID(),product,qty);
	ELSE
        IF (SELECT MAX(idInvoice) FROM Invoice WHERE idCustomer = customer) is null then 
        INSERT INTO Invoice (idCustomer,DateEst) VALUES	(customer,now());
        INSERT INTO InvoiceLine VALUES (last_insert_ID(),product,qty);
		ELSE
        SELECT MAX(idInvoice) into v_invoice FROM Invoice WHERE idCustomer = customer;
		INSERT INTO InvoiceLine VALUES (v_invoice,product,qty);
        END IF;
	END IF;
    UPDATE Product SET stock = stock - qty
    WHERE idProduct = product;
Else 
SELECT "quantité insuffisante";
END IF;
END $
DELIMITER ;

call buyProduct(2,1,2,false);
call buyProduct(2,2,4,true);

call buyProduct(3,2,20,false);
call buyProduct(3,1,3,true);

call buyProduct(2,4,9,false);
call buyProduct(2,5,7,true);

call buyProduct(1,1,9,false);
call buyProduct(1,2,7,true);

call buyProduct(2,1,7,false);
call buyProduct(2,3,6,true);
call buyProduct(2,4,7,true);

call buyProduct(4,4,1,false);
call buyProduct(2,3,7,false); /* +1 pt Mathias et Lucas G3 */

call buyProduct(3,4,6,true);

call buyProduct(4,2,2,true);
call buyProduct(4,1,3,true);

drop function if exists nbInvoice;
delimiter $
create function nbInvoice(customer int) returns integer
BEGIN
IF (select idCustomer FROM Customer WHERE idCustomer = customer)
 is NULL then
	return -1;
ELSE
	return (select count(*) FROM Invoice 
	WHERE idCustomer = customer);
END IF;

END $
Delimiter ;
select nbInvoice(1);

drop function if exists maxInvoice;
delimiter $
create function maxInvoice (customer int) returns float
BEGIN 
declare v_max float ;
IF (select idCustomer from Customer where idCustomer = customer) is null then
	set  v_max:=-1;
ELSE
	select max(temp.amount) into v_max
    from
    (select  i.idinvoice, sum(il.quantity*p.unitPrice) as amount
    from Invoice i JOIN InvoiceLine il on i.idInvoice = il.idInvoice
    JOIN Product p on il.idProduct = p.idProduct
	where i.idCustomer = customer  
	group by i.idInvoice) temp;
end if;
    return v_max;
END $
DELIMITER ;

select maxInvoice(4);

DROP PROCEDURE IF EXISTS state;
delimiter $
CREATE PROCEDURE state(IN idProd integer, OUT att_gen varchar(256), OUT demand varchar(256))
BEGIN
	DECLARE F int default 0;
    DECLARE M int default 0;
    DECLARE Total int default 0;
    
    SELECT SUM(quantity) INTO M FROM Customer c
		JOIN Invoice i ON i.idCustomer = c.idCustomer
        JOIN InvoiceLine il ON il.idInvoice = i.idInvoice
        WHERE il.idProduct = idProd AND gender="m";
    
	SELECT SUM(quantity) INTO F FROM Customer c
		JOIN Invoice i ON i.idCustomer = c.idCustomer
        JOIN InvoiceLine il ON il.idInvoice = i.idInvoice
       WHERE il.idProduct = idProd AND gender="f";
    IF M is NULL then set M:=0; end if;
    IF F is NULL then set F:=0; end if;

    IF M > F THEN 	SET att_gen := "Masculin"; End if;
	IF M<F then SET att_gen := "Feminin"; end if;
	If M=F then SET att_gen := "Neutre"; end if;
    
    SET Total := M + F;
    SET demand := "Low";
    
	IF Total > 13 THEN
		SET demand := "Medium";
	END IF;
        
    IF Total > 26 THEN
		SET demand := "High";
	END IF;
    
END$
delimiter ;
set @g := ' ';
set @d := '';
call state (2, @g, @d);
select @g, @d;

