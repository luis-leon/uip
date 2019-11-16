

--Eliminación de idsolicitud
ALTER TABLE sat_uip.datoscontacto DROP idsolicitud

--Agregar idtipocontacto
ALTER TABLE sat_uip.solicituduip ADD idtipocontacto integer not null;

--Add foreign
ALTER TABLE sat_uip.solicituduip 
ADD FOREIGN KEY (idpersonajuridica) REFERENCES sat_uip.tipocontribuyente(id), 
ADD FOREIGN KEY (idpersonanatural) REFERENCES sat_uip.tipocontribuyente(id), 
ADD FOREIGN KEY (idresultado) REFERENCES sat_uip.resultado(idresultado), 
ADD FOREIGN KEY (idcita) REFERENCES sat_uip.cita(idcita), 
ADD FOREIGN KEY (idestatus) REFERENCES sat_uip.estatus(idestatus), 
ADD FOREIGN KEY (idtipocontacto) REFERENCES sat_uip.tipocontacto(idtipocontacto);

ALTER TABLE sat_uip.cita 
ADD FOREIGN KEY (idrazon) REFERENCES sat_uip.razoncita(idrazoncita);

ALTER TABLE sat_uip.datoscontacto 
ADD FOREIGN KEY (idtipocontacto) REFERENCES sat_uip.tipocontacto(idtipocontacto);

alter table sat_uip.solicitud_uip add primary key (id_solicitud);

--Cambio de nombre
--Tabla solicituduip
ALTER TABLE sat_uip.solicitud_uip rename idsolicitud to id_solicitud;
ALTER TABLE sat_uip.solicitud_uip rename numerosolicitud to numero_solicitud;
ALTER TABLE sat_uip.solicitud_uip rename idpersonajuridica to id_persona_juridica;
ALTER TABLE sat_uip.solicitud_uip rename idpersonanatural to id_persona_natural;
ALTER TABLE sat_uip.solicitud_uip rename informacionSolicitada to informacion_Solicitada;
ALTER TABLE sat_uip.solicitud_uip rename informacionAdicionalSolicitada to informacion_Adicional_Solicitada;
ALTER TABLE sat_uip.solicitud_uip rename idResultado to id_Resultado;
ALTER TABLE sat_uip.solicitud_uip rename idCita to id_Cita;
ALTER TABLE sat_uip.solicitud_uip rename idEstatus to id_Estatus;
ALTER TABLE sat_uip.solicitud_uip rename idtipocontacto to id_tipo_contacto;
ALTER TABLE sat_uip.solicituduip rename to solicitud_uip;

--Tabla tipoIdentificacionPersonal
ALTER TABLE sat_uip.tipoIdentificacionPersonal rename idTipoIdPersonal to id_Tipo_Id_Personal;
ALTER TABLE sat_uip.tipoIdentificacionPersonal rename to tipo_Identificacion_Personal;

--Tabla estatus
ALTER TABLE sat_uip.estatus rename idEstatus to id_Estatus;

--Tabla resultado
ALTER TABLE sat_uip.resultado rename idResultado to id_Resultado;

--Tabla razonCita
ALTER TABLE sat_uip.razonCita rename idRazonCita to id_RazonCita;
ALTER TABLE sat_uip.razonCita rename to razon_Cita;

--Tabla cita
ALTER TABLE sat_uip.cita rename idCita to id_Cita;
ALTER TABLE sat_uip.cita rename fechaHora to fecha_Hora;
ALTER TABLE sat_uip.cita rename idRazon to id_Razon;

--Tabla tipoContacto
ALTER TABLE sat_uip.tipoContacto rename idTipoContacto to id_Tipo_Contacto;

--Tabla datosContacto
ALTER TABLE sat_uip.datosContacto rename idTipoContacto to id_Tipo_Contacto;
ALTER TABLE sat_uip.datosContacto rename detalleContacto to detalle_Contacto;
ALTER TABLE sat_uip.datosContacto rename to datos_contacto;

--Tabla tipocontribuyente
ALTER TABLE sat_uip.tipocontribuyente rename to tipo_contribuyente;

--Tabla tipocontacto
ALTER TABLE sat_uip.tipocontacto rename to tipo_contacto;

--GRANT

grant select on all tables in schema sat_rtu to rol_select_sat_catalogo;
grant all privileges on all tables in schema sat_uip to rol_all_sat_uip;
grant select,insert,update,delete on all tables in schema sat_uip to rol_all_sat_uip;
grant rol_select_sat_catalogo to usuario_uip;
grant rol_select_sat_rtu to usuario_uip;	
grant rol_all_sat_uip to usuario_uip WITH ADMIN OPTION;
GRANT USAGE ON SCHEMA sat_uip TO usuario_uip;

--INSERT

INSERT INTO sat_uip.razon_cita VALUES(1,'Informacion Sensible');

INSERT INTO sat_uip.cita VALUES(1,now(),1,true);
INSERT INTO sat_uip.cita VALUES(2,now(),1,false);
INSERT INTO sat_uip.cita VALUES(3,now(),1,true);

INSERT INTO sat_uip.resultado VALUES(1,'Informacion Solicitada');

INSERT INTO sat_uip.tipo_contribuyente VALUES(1,'Persona Juridica');
INSERT INTO sat_uip.tipo_contribuyente VALUES(2,'Persona Natural');

INSERT INTO sat_uip.estatus VALUES(1,'Finalizada');

INSERT INTO sat_uip.tipo_contacto VALUES(1,'Correo Electronico');

INSERT INTO sat_uip.datos_contacto VALUES(1,'test@gmail.com');

INSERT INTO sat_uip.solicitud_uip VALUES(1,'0001',null,2,'Informacion','Informacion adicional',1,1,1,1);

