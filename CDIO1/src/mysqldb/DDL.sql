drop table if exists operatoer;
drop table if exists raavare;
drop table if exists raavarebatch;
drop table if exists recept;
drop table if exists receptkomponent;
drop table if exists produktbatch;
drop table if exists produktbatchkomponent;

create table operatoer
	(opr_id int(10),
	 opr_navn varchar(20),
	 ini varchar(20),
	 cpr varchar(20),
	 password varchar(20),
	 primary key (opr_id)
	);
--operatoer(opr_id : integer; opr_navn : string; ini : string; cpr : string; password : string)

create table raavare
	(raavare_id int(10),
	 raavare_navn varchar(20),
	 leverandoer varchar(20),
	 primary key (raavare_id)
	);
--raavare(raavare_id : integer; raavare navn : string; leverandoer : string)

create table raavarebatch
	(rb_id int(10),
	 raavare_id int(10),
	 maengde int(10),
	 primary key (rb_id)
	);
--raavarebatch(rb_id : integer; raavare_id : integer; maengde : real)

create table recept
	(recept_id int(10),
	 recept_navn varchar(20),
	 primary key (recept_id)
	);
--recept(recept_id : integer; recept_navn : string)

create table receptkomponent
	(recept_id int(10),
	 raavare_id int(10),
	 nom_netto real(),
	 tolerance real(),
	 foreign key (recept_id) references recept(recept_id)),
 	 foreign key (raavare_id) references raavare(raavare_id))
	);
--receptkomponent(recept_id : integer; raavare_id : integer; nom_netto : real; tolerance : real)

create table produktbatch
	(pb_id int(10),
	 recept_id int(10),
	 status int(10),
	 primary key (pb_id),
 	 foreign key (recept_id) references recept(recept_id));
	);	
--produktbatch(pb_id : integer; recept_id : integer; status : integer)

create table produktbatchkomponent
	(pb_id int(10),
	 rb_id int(10),
	 opr_id int(10),
	 tara real(),
	 netto real(),
 	 foreign key (pb_id) references produktbatch(pb_id)),
 	 foreign key (rb_id) references raavarebatch(rb_id)),
  	 foreign key (opr_id) references operatoer(opr_id))
	);	
--produktbatchkomponent(pb_id : integer; rb_id : integer; opr_id : integer; tara : real; netto : real)
