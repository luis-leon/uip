package gt.gob.sat.expel.task.delegate;

import gt.gob.sat.expel.task.dto.EnvioSolicitudDto;
import gt.gob.sat.expel.task.service.Service;
import java.util.Base64;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import liquibase.logging.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GuardarInfoSolicitudTask
  implements JavaDelegate
{
  
    public void execute(DelegateExecution execution){

           EnvioSolicitudDto solicitud= new EnvioSolicitudDto();
          //EnvioSolicitudDto pruebasolicitud= new EnvioSolicitudDto();
            Service guardar= new Service();
         
             if((String) execution.getVariable("informacionSolicitada")!= null){
                    //realiza la validacion si existe informacion solicitada y cuenta con algun medio para poder comunicarse con el
              if(((String) execution.getVariable("primerNombre"))!=null && 
              ((String) execution.getVariable("primerApellido"))!=null){
                solicitud.setPrimerNombre(((String) execution.getVariable("primerNombre")));
                solicitud.setPrimerApellido(((String) execution.getVariable("primerApellido")));
              }
              else{
                solicitud.setPrimerNombre("Solicitante");
                solicitud.setPrimerApellido("de Información Pública");
              }
              //CORREOELECTRONICO1
              if(((String) execution.getVariable("confirmacionCorreoElectronico1"))!=null){
                solicitud.setCorreoelectronico1(((String) execution.getVariable("confirmacionCorreoElectronico1")));
              }
              else{
                solicitud.setCorreoelectronico1("");
              }
              //CORREO ELECTRONICO 2
              if(((String) execution.getVariable("confirmacionCorreoElectronico2"))!=null){
                solicitud.setCorreoelectronico2(((String) execution.getVariable("confirmacionCorreoElectronico2")));
              }
              else{
                solicitud.setCorreoelectronico2("");
              }
              //ESTATUS
              /* if(((String) execution.getVariable("confirmacionCorreoElectronico2"))!=null){
                solicitud.setEstatus(((String) execution.getVariable("confirmacionCorreoElectronico2")));
              }
              else{
                solicitud.setEstatus("");
              } */
              solicitud.setEstatus(2);
        

              //Direccion fisica
              if(((String) execution.getVariable("direccionFisica"))!=null){
                solicitud.setDireccionFisica(((String) execution.getVariable("direccionFisica")));
              }
              else{
                solicitud.setDireccionFisica("");
              } 

              //FAX
               if(((String) execution.getVariable("fax"))!=null){
                solicitud.setFax(((String) execution.getVariable("fax")));
              }
              else{
                solicitud.setFax("");
              } 
              
              
              //fecha expiraacion
              /* if(((String) execution.getVariable("fechaExpiracion"))!=null){
                solicitud.setFechaExpiracion(((String) execution.getVariable("fechaExpiracion")));
              }
              else{
                solicitud.setFechaExpiracion(null);
              }  */

              //verificar el tipo de datos correspondiente a la fecha
              /* solicitud.setFechaExpiracion(((String) execution.getVariable("fechaExpiracion"))); */
              solicitud.setFechaExpiracion(null);
              solicitud.setInformacionAdicionalSolicitada(((String) execution.getVariable("informacionAdicionalProporcionada")));
              solicitud.setInformacionAdicionalSolicitada(((String) execution.getVariable("informacionAdicionalProporcionada")));
              solicitud.setInformacionSolicitada(((String) execution.getVariable("informacionSolicitada")));
              solicitud.setNit((String) execution.getVariable("nit"));

              //numero identificacion
              if(((String) execution.getVariable("numeroIdentificacion"))!=null){
                solicitud.setNumeroIdentificacion("1");
              }
              else{
                solicitud.setNumeroIdentificacion("0");
              } 

              //numero solicitud
              if(((String) execution.getVariable("numeroSolicitud"))!=null){
                solicitud.setNumeroSolicitud(((String) execution.getVariable("numeroSolicitud")));
              }
              else{
                solicitud.setNumeroSolicitud("");
              }

               //pais emision

               /* if(((String) execution.getVariable("paisEmision"))!=null){
                solicitud.setPaisEmision(((String) execution.getVariable("paisEmision")));
              }
              else{
                solicitud.setPaisEmision(0);
              }
              */
              solicitud.setPaisEmision(49);

              //razon social
              if(((String) execution.getVariable("razonSocial"))!=null){
                solicitud.setRazonSocial(((String) execution.getVariable("razonSocial")));
              }
              else{
                solicitud.setRazonSocial("");
              }
            
              solicitud.setSegundoApellido(((String) execution.getVariable("segundoApellido")));
              solicitud.setSegundoNombre(((String) execution.getVariable("segundoNombre")));
             
            //telefono1
                if(((String) execution.getVariable("confirmacionTelefono1"))!=null){
                solicitud.setTelefono1(((String) execution.getVariable("confirmacionTelefono1")));
              }
              else{
                solicitud.setTelefono1("");
              }

              //telefono2
              if(((String) execution.getVariable("confirmacionTelefono2"))!=null){
                solicitud.setTelefono1(((String) execution.getVariable("confirmacionTelefono2")));
              }
              else{
                solicitud.setTelefono2("");
              }

              solicitud.setTercerApellido(((String) execution.getVariable("tercerApellido")));
              solicitud.setTercerNombre(((String) execution.getVariable("tercerNombre")));
              solicitud.setTipoIdentificacionPersonal(1);
              
              //vencimiento representacion legal
              if(((String) execution.getVariable("vencimientoRepresentacionLegal"))!=null){
               // solicitud.setVencimientoRepresentacionLegal(((String) execution.getVariable("vencimientoRepresentacionLegal")));
              }
              else{
                solicitud.setVencimientoRepresentacionLegal(null);
              }
              

              

              //para que los correos sean desviados 
             // execution.setVariable("paraCorreo", ("alfdeleon@sat.gob.gt"));

              int resp=guardar.GuardarSolicitud(solicitud);
              execution.setVariable("idEstatus", solicitud.getEstatus());
              //execution.setVariable("nombreUsuario", (solicitud.respuestas() + " --"+ resp));  
              execution.setVariable("idSolicitud", (resp));                
            }
            else
            {
              //execution.setVariable("nombreUsuario", "no existe informacion");  
              //colocar el log de que no iban los campos llenos por eso no guarda 
            }
            
      }
    
}