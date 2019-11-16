/*
 * author: alfdeleon
 * Creado para la gestion aleatoria de revisores
 * */

-- creación de esquema, si ya existe OMITIR
/*CREATE SCHEMA sat_uip
    AUTHORIZATION postgres;
   	create user usuario_uip password 'desa123$';
  	create role rol_all_sat_uip;
  	grant all privileges on all tables in schema sat_uip to rol_all_sat_uip;
	grant select,insert,update,delete on all tables in schema sat_uip to rol_all_sat_uip;
	GRANT USAGE ON SCHEMA sat_uip TO usuario_uip;
*/
  
--EJECUTAR COMO DBA
create table sat_uip.colaborador_revisor(
	id_revisor integer not null,
	nombre varchar(100) not null,
	correo varchar(100) not null,
	primary key (id_revisor)
);
insert into sat_uip.colaborador_revisor values (1,'revisor1','revisor1@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (2,'revisor2','revisor2@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (3,'revisor3','revisor3@sat.gob.gt');

insert into sat_uip.colaborador_revisor values (4,'Analista1','analista1@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (5,'Analista2','analista2@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (6,'Analista3','analista3@sat.gob.gt');

insert into sat_uip.colaborador_revisor values (7,'Enlace UIP1','enlaceUIP1@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (8,'Enlace UIP2','enlaceUIP2@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (9,'Enlace UIP3','enlaceUIP3@sat.gob.gt');

insert into sat_uip.colaborador_revisor values (10,'Enlace UA1','enlaceUA1@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (11,'Enlace UA2','enlaceUA2@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (12,'Enlace UA3','enlaceUA3@sat.gob.gt');

insert into sat_uip.colaborador_revisor values (13,'Encargado UIP1','encargadoUIP1@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (14,'Encargado UIP2','encargadoUIP2@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (15,'Encargado UIP3','encargadoUIP@sat.gob.gt');

insert into sat_uip.colaborador_revisor values (16,'Responsable Departamento de Consultas1','consultas1@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (17,'Responsable Departamento de Consultas2','consultas2@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (18,'Responsable Departamento de Consultas3','consultas3@sat.gob.gt');

insert into sat_uip.colaborador_revisor values (19,'Notificador CC1','notificadorCC1@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (20,'Notificador CC2','notificadorCC2@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (21,'Notificador CC3','notificadorCC3@sat.gob.gt');

insert into sat_uip.colaborador_revisor values (22,'Notificador1','notificador1@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (23,'Notificador2','notificador2@sat.gob.gt');
insert into sat_uip.colaborador_revisor values (24,'Notificador3','notificador3@sat.gob.gt');

--------------------------------------------------------------------------------
create table sat_uip.cat_revisores(
	id_cat integer not null,
	descripcion_cargo varchar (100) not null, 
	ultimo_asignado integer null,
	primary key(id_cat)
);
insert into sat_uip.cat_revisores values (1,'Revisor');
insert into sat_uip.cat_revisores values (2,'Analista');
insert into sat_uip.cat_revisores values (3,'Enlace UIP');
insert into sat_uip.cat_revisores values (4,'Enlace UA');
insert into sat_uip.cat_revisores values (5,'Encargado UIP');
insert into sat_uip.cat_revisores values (6,'Responsable Departamento de Consultas');
insert into sat_uip.cat_revisores values (7,'Notificador CC');
insert into sat_uip.cat_revisores values (8,'Notificador');

--CREACION DE REVISORES DEFINIDOS 19/8/2019
insert into sat_uip.cat_revisores values (9,'Gerencia Administrativa Financiera');
insert into sat_uip.cat_revisores values (10,'Intendencia de Atención al Contribuyente');
insert into sat_uip.cat_revisores values (11,'Intendencia de Aduanas');
insert into sat_uip.cat_revisores values (12,'Intendencia de Asuntos Jurídicos');
insert into sat_uip.cat_revisores values (13,'Intendencia de Fiscalizacion');
insert into sat_uip.cat_revisores values (14,'Intendencia de Recaudación');
insert into sat_uip.cat_revisores values (15,'Gerencia de Asesoria Técnica del Directorio');
insert into sat_uip.cat_revisores values (16,'Gerencia de Auditoría Interna');
insert into sat_uip.cat_revisores values (17,'Gerencia de Contribuyentes Especiales Grandes');
insert into sat_uip.cat_revisores values (18,'Gerencia de Contribuyentes Especiales Grandes');
insert into sat_uip.cat_revisores values (19,'Gerencia de Gestión de Recursos');
insert into sat_uip.cat_revisores values (20,'Gerencia de Informática');
insert into sat_uip.cat_revisores values (21,'Gerencia de Infraestructura');
insert into sat_uip.cat_revisores values (22,'Gerencia de Orientación Legal y Derechos del Contribuyente');
insert into sat_uip.cat_revisores values (23,'Gerencia de Planificación y Cooperación');
insert into sat_uip.cat_revisores values (24,'Gerencia de Recursos Humanos');
insert into sat_uip.cat_revisores values (25,'Gerencia Regional Central');
insert into sat_uip.cat_revisores values (26,'Gerencia Regional Nororiente');
insert into sat_uip.cat_revisores values (27,'Gerencia Regional Occidente');
insert into sat_uip.cat_revisores values (28,'Gerencia Regional Sur');
insert into sat_uip.cat_revisores values (29,'Gerencia de Secretaría General');
insert into sat_uip.cat_revisores values (30,'Gerencia de Seguridad Institucional');
insert into sat_uip.cat_revisores values (31,'Politica de Criterios Tributarios');
insert into sat_uip.cat_revisores values (32,'Politicas Institutcionales');
insert into sat_uip.cat_revisores values (33,'Unidad de Comunicación Social Externa');
insert into sat_uip.cat_revisores values (34,'Unidad de Cultura Tributaria');
insert into sat_uip.cat_revisores values (35,'Gerencia de Formación de Personal');
insert into sat_uip.cat_revisores values (36,'Gerencia de Investigación Fiscal');
insert into sat_uip.cat_revisores values (37,'Unidad de Tribunal Administrativo y Financiero');

------------------------------------------------------------------------------
create table sat_uip.asignacion(
	id_asignacion integer not null,
	id_revisor integer not null,
	id_cat integer not null,
	id_ord_revisor integer not null,
	estatus varchar(2) not null,
	primary key (id_asignacion),
	FOREIGN KEY (id_revisor) REFERENCES  sat_uip.colaborador_revisor(id_revisor),
	FOREIGN KEY (id_cat) REFERENCES  sat_uip.cat_revisores(id_cat)
);
insert into sat_uip.asignacion values(1,1,1,1,'A');
insert into sat_uip.asignacion values(2,2,1,2,'A');
insert into sat_uip.asignacion values(3,3,1,3,'A');

insert into sat_uip.asignacion values(4,4,2,1,'A');
insert into sat_uip.asignacion values(5,5,2,2,'A');
insert into sat_uip.asignacion values(6,6,2,3,'A');

insert into sat_uip.asignacion values(7,7,3,1,'A');
insert into sat_uip.asignacion values(8,8,3,2,'A');
insert into sat_uip.asignacion values(9,9,3,3,'A');

insert into sat_uip.asignacion values(10,10,4,1,'A');
insert into sat_uip.asignacion values(11,11,4,2,'A');
insert into sat_uip.asignacion values(12,12,4,3,'A');

insert into sat_uip.asignacion values(13,13,5,1,'A');
insert into sat_uip.asignacion values(14,14,5,2,'A');
insert into sat_uip.asignacion values(15,15,5,3,'A');

insert into sat_uip.asignacion values(16,16,6,1,'A');
insert into sat_uip.asignacion values(17,17,6,2,'A');
insert into sat_uip.asignacion values(18,18,6,3,'A');

insert into sat_uip.asignacion values(19,19,7,1,'A');
insert into sat_uip.asignacion values(20,20,7,2,'A');
insert into sat_uip.asignacion values(21,21,7,3,'A');

insert into sat_uip.asignacion values(22,22,8,1,'A');
insert into sat_uip.asignacion values(23,23,8,2,'A');
insert into sat_uip.asignacion values(24,24,8,3,'A');




