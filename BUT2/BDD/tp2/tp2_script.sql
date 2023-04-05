SET default_storage_engine= InnoDB;
SET SQL_SAFE_UPDATES=0;

drop table if exists Inventaire;

create table Inventaire
(

idProduct int,
designation varchar(254),
prix float,
demande varchar(256),
sexe_attire varchar(256),

constraint PK_Inventaire primary key (idProduct),
constraint FK_Inventaire_Product foreign key (idProduct) references Product(idProduct)

);

-- exercice 1

drop procedure if exists doInventory;

delimiter $

create procedure doInventory()
begin
	
    declare cpt int;
    declare tmp_idProduct int;
    declare tmp_Designation varchar(256);
    declare tmp_prix float;
	declare sexe_at varchar(256);
	declare demande varchar(256);
    
    declare curseur cursor for 
    select idProduct, designation, unitPrice from Product;
    
    select count(distinct(idProduct)) into cpt from Product;
    
    open curseur;
    while cpt > 0 do
		fetch curseur into tmp_idProduct, tmp_designation, tmp_prix;
        CALL state(tmp_idProduct, sexe_at, demande);
		
        insert into Inventaire
        values (tmp_idProduct, tmp_designation, tmp_prix, sexe_at, demande);
        set cpt = cpt -1;
    end while;
    
    close curseur;
end $
delimiter ;

CALL doInventory();

select * from Inventaire;



-- exercice 2

drop function if exists maxInvoice2;

delimiter $

create function maxInvoice2(customer int)
returns float
begin
	
    declare max_amount float default 0;
    declare tmp_amount float;
    declare tmp_idInvoice int;
    declare fincurs bool default 0;
    
    
	declare curseur cursor for
    select  i.idinvoice, sum(il.quantity*p.unitPrice) as amount
    from Invoice i JOIN InvoiceLine il on i.idInvoice = il.idInvoice
    JOIN Product p on il.idProduct = p.idProduct
	where i.idCustomer = customer  
	group by i.idInvoice;
	
    declare continue handler for NOT fOUND set fincurs = 1;
    
    open curseur;
    
    fetch curseur into tmp_idInvoice, tmp_amount;
    while not fincurs do
		if (tmp_amount > max_amount) then 
			set max_amount = tmp_amount;
		end if;
		fetch curseur into tmp_idInvoice, tmp_amount; 
    end while;
    close curseur;
    return max_amount;
    
end $ 
delimiter ;

select maxInvoice2(1);

-- exercice 3


alter table Customer add categorie varchar(15);

drop procedure if exists completeCategorie;

delimiter $

create procedure completeCategorie()
begin 

	declare cptr int default 0;
    declare tmp_idCustomer int;
    declare tmp_amount int;    
	
	declare curseur cursor for 
	select i.idCustomer, sum(il.quantity*p.unitPrice) as amount
    from Invoice i JOIN InvoiceLine il on i.idInvoice = il.idInvoice
    JOIN Product p on il.idProduct = p.idProduct
    join Customer c on c.idCustomer = i.idCustomer
    where c.categorie is null
	group by i.idCustomer;
    
    select count(idCustomer) into cptr
    from Customer 
    where categorie is null;
    
    open curseur;
    
    while cptr > 0 do
    
		fetch curseur into tmp_idCustomer, tmp_amount;
		case 
			when (tmp_amount < 100) then 
				update Customer set categorie = 'potentiel' where idCustomer = tmp_idCustomer;
			when (tmp_amount > 100 and tmp_amount < 3000) then
				update Customer set categorie = 'ordinaire' where idCustomer = tmp_idCustomer;
			when (tmp_amount > 3000) then 
				update Customer set categorie = 'VIP' where idCustomer = tmp_idCustomer;
		end case;
        
		set cptr = cptr-1;
    end while;
    close curseur;
end $
delimiter ;

call completeCategorie();


-- exercice 4

drop procedure if exists mostOrdered;

delimiter $

create procedure mostOrdered( in customer int, INOUT product varchar(500), inout qty int)
begin 
	
    declare tmp_designation varchar(254);
    declare tmp_qty int;
    
    declare fincurs bool default 0;
	
	declare curseur cursor for
	select  p.designation, sum(il.quantity)
    from Invoice i JOIN InvoiceLine il on i.idInvoice = il.idInvoice
    JOIN Product p on il.idProduct = p.idProduct
    where i.idCustomer = customer
	group by p.designation;
    
    declare continue handler for not found set fincurs = 1;
    
    open curseur;
    
    fetch curseur into tmp_designation, tmp_qty;
    
    while not fincurs do
		
        if(tmp_qty > qty) then 
			set qty = tmp_qty;
            set product = tmp_designation;
		end if;
		if (tmp_qty = qty) then
			set product = concat(tmp_designation, '_', product);
		end if;
        
        fetch curseur into tmp_designation, tmp_qty;
        
	end while;
    close curseur;
    
end $
delimiter ;

-- exercice 5


drop trigger if exists updateProductPrice;
drop trigger if exists insertProduct;

create table ProductEvolution
(

idProduct int,
price float,
dateEvolution datetime,

constraint FK_ProductEvolution foreign key (idProduct) references Product(idProduct)

);

delimiter $
create trigger updateProductPrice
after update on Product for each row
begin
	if (new.unitPrice <> old.unitPrice) then
		insert into ProductEvolution
		values (new.idProduct, new.unitPrice, now());
	end if;
end $
delimiter ;

delimiter $
create trigger insertProduct
after insert on Product for each row
begin
	insert into ProductEvolution
	values (new.idProduct, new.unitPrice, now());
end $
delimiter ;

/*insert into Product
values (default, "produit test", 15.0, 150);*/

update Product set unitPrice = 12.0
where idProduct = 7;

-- exercice 6

drop trigger if exists detect100Client;
drop trigger if exists factureSup500;

create table Journal
(

envent_ varchar(256),
date_ timestamp

)

-- 1)
delimiter $
create trigger detect100Client
after insert on Customer for each row
begin

	if (new.idCustomer = 100) then
		insert into Journal
        value ("100ème client", now());
	end if;
end $
delimiter ;


-- 2)

delimiter $
create trigger factureSup500
after insert on InvoiceLine for each row
begin 
	declare montant float;
    
	select sum(quantity * p.unitPrice) amount into montant from InvoiceLine il
	join Product p on il.idProduct = p.idProduct
	join Invoice i on il.idInvoice = i.idInvoice
    where il.idInvoice = new.idInvoice
	group by il.idInvoice;
    
    if (montant > 500) then
		signal sqlstate '45000' set mysql_errno=30001, message_text="necessite une verification d'identité";
	end if;
end $
delimiter ;

-- 3)