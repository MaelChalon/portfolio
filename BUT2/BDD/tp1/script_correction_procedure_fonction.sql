/*==============================================================*/
/* Question : 01                                                */
/*==============================================================*/

  /* 
  Remarque :
  On a mis la valeur 0 dans le champ CustomerID car c’est une clé auto incrémentée. 
  La valeur 0 sera ignorée et le SGBD insera automatiquement le bon ID en incrémantant 
  de 1 la  valeur maximale de IDCutomer dans la table Customer.
  */ 

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
   select "op ok";
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



/*==============================================================*/
/* Question : 04                                                */
/*==============================================================*/ 



drop procedure if exists buyProduct;

/* 
    Solution 01 : 
    la derniere facture est la facture ayant la valeur ID la plus grande
*/	


delimiter $
create procedure buyProduct (IN customer int,
IN product int,
IN qty int, 
IN last_invoice boolean)
BEGIN 
 declare v_invoice integer; 
IF qty < (SELECT stock FROM Product where idProduct = product) then 
    IF last_invoice is false then 
		INSERT INTO Invoice (idCustomer,DateEst) VALUES	(customer,now());
        INSERT INTO InvoiceLine VALUES (last_insert_ID(),product,qty);
	ELSE
        IF (SELECT MAX(idInvoice) FROM Invoice WHERE idCustomer = customer) is null then 
        INSERT INTO Invoice (idCustomer,DateEst) VALUES	(customer,now());
		END IF;
        SELECT MAX(idInvoice) into v_invoice FROM Invoice WHERE idCustomer = customer;
		INSERT INTO InvoiceLine VALUES (v_invoice,product,qty);
	END IF;
    UPDATE Product SET stock = stock - qty
    WHERE idProduct = product;
Else 
SELECT "quantité insuffisante";
END IF;
END $
DELIMITER ;

/* 
    Solution 02 (Autre variante):
    la derniere facture est la facture la plus récente (en termes de date d'établissement)
*/	

drop procedure if exists buyProduct;

delimiter $
CREATE  PROCEDURE buyProduct( IN customer int, IN prod INT, IN qty int, IN last_invoice boolean)
   begin
    declare stock_product int default 0;
    declare last_invoice_id int default 0;
	
	/* verifier si le stock est suffisant */
    select stock into stock_product  from Product where Product.idProduct = prod;
	
    IF stock_product >= qty then
		/* verifier si le client a une facture ou PAS
           Si oui : recupere id de la derniere facture (la plus récente)
           Sinon :  valeur 0 par default (car null ne vas modifier la valeur par default de var last_invoice_id
		*/
		Select i.idInvoice into last_invoice_id  from Invoice i where i.idCustomer = customer
        and i.DateEst= (select max(Invoice.DateEst) from
		Invoice where Invoice.idCustomer = customer); /* la facture la plus récente*/
		
        IF ( last_invoice_id >0 and last_invoice = True ) then
          Select "Insertion dans la facture",last_invoice_id; /*c'est juste un affichage*/
        Else 
           /* créer une nouvelle facture*/
           insert into Invoice values(0,customer,current_timestamp());
           /* ranger l'id de la nouvelle facture dans last_invoice_id*/
           select last_insert_id() into last_invoice_id; 
          Select "ID de la nouvelle facture",last_invoice_id;
        End If; /* fin 2eme if else*/
        /* inserer l'achat dans invoiceline (last_invoice_id,prod)*/
        Insert into InvoiceLine values(last_invoice_id,prod,qty);
        /* mettre à jour le stock*/
        Update Product set Product.stock = Product.stock - qty where Product.idProduct = prod;
    ELSE
		Select "Stock insuffisant";
    END IF; /* fin 1er if else*/
	
	
	
   end $
delimiter ; 

/* Insertion des données
Remarque : la procedure n'insere qu'un seul achat à la fois.
Client 2 achète produit 1 en qte 2, produit 2 en qte 4 sur la même facture
pour inserer les achat du client 2, faut appeler la procedure 2 fois
call buyProduct(2,1,2,false); créer une nouvelle facture et inserer 1er achat
call buyProduct(2,2,4,true); inserer le deuxieme achat dans la meme facture
 */ 



call buyProduct(2,1,2,false);
call buyProduct(2,2,4,true);

call buyProduct(3,2,20,false);
call buyProduct(3,1,3,true);

call buyProduct(2,4,9,false);
call buyProduct(2,5,7,true);

call buyProduct(1,1,9,false);
call buyProduct(1,2,7,true);

call buyProduct(2,1,7,false);
call buyProduct(2,4,7,true);

call buyProduct(4,4,1,false);

call buyProduct(3,4,6,true);

call buyProduct(4,2,2,true);
  

 
  
/*==============================================================*/
/* Question : 05                                                */
/*==============================================================*/ 

Drop procedure if exists supplyProduct ;
DELIMITER $
create procedure supplyProduct( IN designation varchar(254), IN unitPr Float, IN qty int)
BEGIN
	/******* if it exists **************/ 
	DECLARE id int default 0;
	
	Select Product.idProduct into id from Product where Product.designation= designation;
	IF id =0  THEN
	       Select "Product does not exist";
	ELSE
		Update Product set Product.stock = Product.stock + qty, Product.unitPrice = unitPr where Product.idProduct = id;
	END IF;
END $
DELIMITER ;

/* ===========================================

partie 3

=========================================== */



DELIMITER $

create function nbInvoice( idc integer )
returns integer 
begin 
	declare nb int default -1;
    if( select idCustomer from Customer where idCustomer = idc ) then 
		select count(idINvoice) into nb from Invoice where idCustomer = idc;
	end if;
	
    return nb ;
end $
DELIMITER ;




select i.idInvoice, i.idCustomer, sum(il.quantity * p.unitPrice) as total from Invoice i
join InvoiceLine il on i.idInvoice = il.idInvoice
join Product p on p.idProduct = il.idProduct
group by i.idInvoice;

drop function if exists maxInvoice;

delimiter $

create function maxInvoice( idc integer )
returns float
begin
	
    declare retour float default 0;
    
    select max(a.total) into retour from
	(select sum(il.quantity * p.unitPrice) as total from Invoice i
	join InvoiceLine il on i.idInvoice = il.idInvoice
	join Product p on p.idProduct = il.idProduct
	where i.idCustomer = idc
	group by i.idInvoice) a;
    
    return retour;
end $
delimiter ;

select maxInvoice( 2 );

drop function if exists minIvoice;

delimiter $

create function minIvoice( idc integer )
returns float
begin
	
    declare retour float default 0;
    
    select min(a.total) into retour from
	(select sum(il.quantity * p.unitPrice) as total from Invoice i
	join InvoiceLine il on i.idInvoice = il.idInvoice
	join Product p on p.idProduct = il.idProduct
	where i.idCustomer = idc
	group by i.idInvoice) a;
    
    return retour;
end $
delimiter ;

drop function if exists avgIvoice;

delimiter $

create function avgIvoice( idc integer )
returns float
begin
	
    declare retour float default 0;
    
    select avg(a.total) into retour from
	(select sum(il.quantity * p.unitPrice) as total from Invoice i
	join InvoiceLine il on i.idInvoice = il.idInvoice
	join Product p on p.idProduct = il.idProduct
	where i.idCustomer = idc
	group by i.idInvoice) a;
    
    return retour;
end $
delimiter ;



-- 3)

select sum(il.quantity) quantité_par_genre, c.gender from Invoice i
join InvoiceLine il on i.idInvoice = il.idInvoice
join Customer c on i.idCustomer = c.idCustomer
where il.idProduct = 1 and c.gender = "m";

drop procedure if exists state;

delimiter $

create procedure state( in product int, out gender_att varchar(10), out demand varchar(10) )
begin 
	
    declare homme integer;
    declare femme integer;
    declare total integer default 0;
    
    if(select idProduct from Product where idProduct = product) then
		
        select sum(il.quantity) quantité_par_genre into homme from Invoice i
		join InvoiceLine il on i.idInvoice = il.idInvoice
		join Customer c on i.idCustomer = c.idCustomer
		where il.idProduct = product and c.gender = "m";
        
        select sum(il.quantity) quantité_par_genre into femme from Invoice i
		join InvoiceLine il on i.idInvoice = il.idInvoice
		join Customer c on i.idCustomer = c.idCustomer
		where il.idProduct = product and c.gender = "f";
        
        if homme is null then set homme = 0; end if;
        if femme is null then set femme = 0; end if;
        
        if homme > femme then set gender_att= "homme"; end if;
        if femme > homme then set gender_att= "femme"; end if;
        if homme = femme then set gender_att= "neutre"; end if;
        
        set total = homme + femme;
        set demand = "faible";
        if total > 500 then set demand = "moyenne"; end if;
        if total > 1000 then set demand = "forte"; end if;
        
	else
		set gender_att = "inconnue";
        set demand = "inconnue";
    end if;
end $

delimiter ;

set @g = " ";
set @d = " ";
call state(2, @g, @d);

select @g, @d;


s