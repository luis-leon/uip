--Agregar tablas
create table sat_uip.persona_natural(
	id_persona_natural integer primary key,
	primer_nombre varchar not null,
	segundo_nombre varchar,
	tercer_nombre varchar,
	primer_apellido varchar,
	segundo_apellido varchar,
	tercer_apellido varchar,
	numero_identificacion varchar,
	id_tipo_identificacion_oficial integer,
	id_pais_emision_pasaporte integer
);

CREATE TABLE

Query returned successfully in 85 msec.

create table sat_uip.pais(
	id_pais integer primary key,
	nombre varchar
);

CREATE TABLE

Query returned successfully in 331 msec

create table sat_uip.persona_juridica(
	id_contribuyente integer primary key,
	razon_social varchar not null
);

CREATE TABLE

Query returned successfully in 203 msec.

create table sat_uip.contribuyente(
	id_contribuyente integer primary key,
	nit varchar not null
);
CREATE TABLE

Query returned successfully in 95 msec.
--Agregar tablas


--insert para tipo contacto

insert into sat_uip.tipo_contacto values(2, 'Telefono 1');
insert into sat_uip.tipo_contacto values(3, 'Telefono 2');
insert into sat_uip.tipo_contacto values(4, 'Corrreo Electronico 2');
insert into sat_uip.tipo_contacto values(5, 'Direccion Fisica');
insert into sat_uip.tipo_contacto values(6, 'Fax');
insert into sat_uip.tipo_identificacion_personal values(1, 'NIT');
insert into sat_uip.tipo_identificacion_personal values(2, 'CUI');
insert into sat_uip.tipo_identificacion_personal values(3, 'Pasaporte');
INSERT 0 1

Query returned successfully in 158 msec.

--insert para tipo contacto