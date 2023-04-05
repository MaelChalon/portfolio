SET default_storage_engine= InnoDB;
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