create table usuarios(
	id serial primary key,
	nome varchar(50) not null,
	email varchar(255) not null,
	senha varchar(50) not null,
	ativo boolean default true
);
create table quadros(
	id serial primary key,
	titulo varchar(50) not null,
	descricao text not null,
	publico boolean default false,
	id_usuario integer,
	foreign key (id_usuario) references usuarios(id)
);
create table colunas(
	id serial primary key,
	titulo varchar(50) not null,
	ordem integer,
	id_quadro integer,
	foreign key (id_quadro) references quadros(id)
);
create table cartoes(
	id serial primary key,
	titulo varchar(50) not null,
	status boolean default false,
	ordem integer,
	data_inicio date,
	data_termino date,
	data_atualizacao date not null default current_timestamp,
	id_coluna integer,
	foreign key (id_coluna) references colunas(id)
);
create table etiquetas(
	id serial primary key,
	titulo varchar(50) not null,
	cor varchar(50) default 'deafult',
	id_quadro integer,
	foreign key (id_quadro) references quadros(id)
);
create table cartao_etiquetas(
	id serial primary key,
	id_etiqueta integer,
	id_cartao integer,
	foreign key (id_etiqueta) references etiquetas(id),
	foreign key (id_cartao) references cartoes(id)
);