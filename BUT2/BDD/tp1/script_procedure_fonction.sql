drop procedure if exists ajout_client;
drop procedure if exists ajout_produit;
drop procedure if exists buyProduct;



 -- 1)

DELIMITER $
create procedure ajout_client( IN nom varchar(25), IN prenom varchar(25), IN adresse varchar(254), IN naissance datetime, IN num varchar(10), IN sexe char(1))
begin
insert into Customer values(0, nom, prenom, adresse, naissance, num, sexe);

select "insertion faite";
end
$

-- 2)

DELIMITER $
create procedure ajout_produit(IN designation varchar(254), IN unitPrice float, IN stock int(11))
begin
insert into Product values(0, designation, unitPrice, stock);

select "insertion faite";
end
$

-- 3)



-- 4)

DELIMITER $
create procedure buyProduct( IN customer int, IN product int, IN qty int, IN last_invoice boolean)
begin

if last_invoice = true then
	if isnull((select idInvoice from Invoice where idCustomer = customer)) then
		insert into Invoice values(0, customer, now());
	end if;
    if qty > (select stock from Product where idProduct = product) then 
		select "stock insufisant";
	else
		insert into InvoiceLine values((select idInvoice from Invoice where Customer = customer and DateEst = (select max(dateEst) from Invoice))
										, product, qty);
		update Product set stock = (select stock from Product where idProduct = product) - qty;
	end if;
else
	insert into Invoice values(0, customer, now());
    if qty > (select stock from Product where idProduct = product) then 
		select "stock insufisant";
	else
		insert into InvoiceLine values((select idInvoice from Invoice where Customer = customer and DateEst = (select max(dateEst) from Invoice))
										, product, qty);
		update Product set stock = (select stock from Product where idProduct = product) - qty;
	end if;
end if;

end
$

/*CALL `p2103455`.`ajout_client`("Coulomb", "Francois", "4 rue liberte", 19800212, 06456780, 'm');
CALL `p2103455`.`ajout_client`("Dupont", "Bernard", "120, square zola", 19720812, 01345678, 'm');
CALL `p2103455`.`ajout_client`("Corda", "Nathalie", "66, bd napoleon III ", 19770210, 06455790, 'f');
CALL `p2103455`.`ajout_client`("Pierre", "Alexandra", "1, place des martyres", 19720908, 01645870, 'f');


CALL `p2103455`.`ajout_produit`( "DVD Matrix", 23.5, 100);
CALL `p2103455`.`ajout_produit`( "DVD The island", 49.30, 100);
CALL `p2103455`.`ajout_produit`( "CD Album Yanni", 25.9, 100);
CALL `p2103455`.`ajout_produit`( "DVD DIvergent", 10.00, 5);
CALL `p2103455`.`ajout_produit`( "DVD Alegiant", 12.00, 5);
CALL `p2103455`.`ajout_produit`( "DVD Insurgent", 11.5, 5);*/


CALL `p2103455`.`buyProduct`( 2, 2, 4, true);



