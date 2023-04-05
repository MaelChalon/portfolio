drop table if exists ProductEvolution;
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
delimiter $;

delimiter $
create trigger insertProduct
after insert on Product for each row
begin
	insert into ProductEvolution
	values (new.idProduct, new.unitPrice, now());
end $
delimiter $;

/*insert into Product
values (default, "produit test", 15.0, 150);*/

update Product set unitPrice = 12.0
where idProduct = 7;