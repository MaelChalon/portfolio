-- question 1


drop function if exists lucrativeMonth;

select sum(p.unitPrice * quantity)
from Invoice i
join InvoiceLine il on i.idInvoice = il.idInvoice
join Product p on il.idProduct = p.idProduct
where dateEst like concat("2022","%") and month(DateEst) = 10
group by (monthname(DateEst))
order by DateEst;


delimiter $

create function lucrativeMonth( years varchar(4))
returns varchar(10)
begin 

	declare mois varchar(10);
    declare mois_tmp varchar(10);
    declare maxi int default 0;
    declare tmp int default 1;
    declare max_tmp int default 0;
    
    boucle: loop
		if tmp = 13 then leave boucle; end if;
        
        select sum(p.unitPrice * quantity) into max_tmp
		from Invoice i
		join InvoiceLine il on i.idInvoice = il.idInvoice
		join Product p on il.idProduct = p.idProduct
		where dateEst like concat(years,"%") and month(DateEst) = tmp
		group by (monthname(DateEst))
		order by DateEst;
        
        select monthname(DateEst) into mois_tmp
		from Invoice i
		join InvoiceLine il on i.idInvoice = il.idInvoice
		join Product p on il.idProduct = p.idProduct
		where dateEst like concat(years,"%") and month(DateEst) = tmp
		group by (monthname(DateEst))
		order by DateEst;

		if max_tmp > maxi then set maxi = max_tmp; set mois = mois_tmp; end if;
	
		set tmp = tmp + 1;
	end loop;
	return mois;
end $
delimiter ;

select lucrativeMonth("2022");


-- question 2

drop procedure if exists effectiveSale;

select count(distinct (idCustomer)) nb from Invoice i
join InvoiceLine il on i.idInvoice = il.idInvoice
where il.quantity > 2 and il.idProduct = 2;

delimiter $

create procedure effectiveSale ( in product int, in qty int, out nb int)
begin 
	
    if product is null then
		select count(distinct(idCustomer)) into nb from Invoice i
		join InvoiceLine il on i.idInvoice = il.idInvoice
		where il.quantity > qty;
    else
		select count(distinct (idCustomer)) into nb from Invoice i
		join InvoiceLine il on i.idInvoice = il.idInvoice
		where il.quantity > qty and il.idProduct = product;
    end if;
end $
delimiter ;

set @nb = 100;

call effectiveSale( 1, 2, @nb );

select (@nb);