CREATE TABLE USUARIO(
	idusuario 		serial 			primary key,
	nome			varchar(150)	not null,
	email			varchar(100)	not null,
	senha			varchar(50)		not null
);

CREATE TABLE CONTA(
	idconta			serial			primary key,
	nome			varchar(150)	not null,
	valor			decimal(18,2)	not null,
	data			date			not null,
	tipo			integer			not null,
	observacoes		varchar(500)	not null,
	idusuario		integer			not null,
	foreign key(idusuario) references usuario(idusuario)
);