Querys

CREATE SCHEMA sat_uip
    AUTHORIZATION postgres;
	
----CREACION DE USUARIO PARA LA GESTION EN LOS DOS ESQUEMAS EJECUCION CON DBA
create user usuario_uip password 'desa123$';
create role rol_select_sat_catalogo;
create role rol_all_sat_uip;

--grant select on all tables in schema sat_catalogo to rol_select_sat_catalogo;

--grant all privileges on all tables in schema sat_uip to rol_all_sat_uip;
grant select,insert,update,delete on all tables in schema sat_uip to rol_all_sat_uip;
grant rol_select_sat_catalogo to usuario_uip;
grant rol_select_sat_rtu to usuario_uip;
grant rol_all_sat_uip to usuario_uip WITH ADMIN OPTION;

--GRANT USAGE ON SCHEMA sat_rtu TO usuario_uip;
--GRANT USAGE ON SCHEMA sat_catalogo TO usuario_uip;
GRANT USAGE ON SCHEMA sat_uip TO usuario_uip;

create table sat_uip.tipoContribuyente(
	id integer not null primary key,
	nombre varchar(40) not null
);

create table sat_uip.tipoIdentificacionPersonal(
	idTipoIdPersonal integer not null primary key,
	nombre varchar(40) not null
);


create table sat_uip.estatus(
	idEstatus integer not null primary key,
	nombre varchar(40) not null
);

create table sat_uip.resultado(
	idResultado integer not null primary key,
	nombre varchar(40) not null
);



create table sat_uip.razonCita(
	idRazonCita integer not null primary key,
	nombre varchar(40)
);


create table sat_uip.cita(
	idCita integer not null primary key,
	fechaHora date not null,
	idRazon integer not null,
	asistio boolean not null
);

create table sat_uip.tipoContacto(
	idTipoContacto integer not null primary key,
	nombre varchar(40) not null
);

create table sat_uip.datosContacto(
	idSolicitud integer not null,
	idTipoContacto integer not null,
	detalleContacto varchar(256) not null
);

alter table sat_uip.datosContacto add constraint PK_DATOS_CONTACTO primary key(idSolicitud, idTipoContacto);


create table sat_uip.solicitudUIP(
	idSolicitud integer not null primary key,
	numeroSolicitud varchar(40) not null,
	idPersonaJuridica integer,
	idPersonaNatural integer,
	informacionSolicitada varchar(512) not null,
	informacionAdicionalSolicitada varchar(512),
	idResultado integer,
	idCita integer,
	idEstatus integer not null
);
