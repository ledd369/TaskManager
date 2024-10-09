create table proyecto(
	id bigint auto_increment PRIMARY KEY,
	nombre varchar(100),
	descripcion varchar(255)
	
);

create table tarea(
	id bigint auto_increment PRIMARY KEY,
	proyecto_id bigint not null,
	nombre varchar(100) not null,
	descripcion varchar(255) not null,
	estado varchar(50) not null,
	foreign key (proyecto_id) REFERENCES proyecto(id)
);
