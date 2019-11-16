package gt.gob.sat.expel.task.delegate;

import gt.gob.sat.expel.task.dto.ModificarSolicitud;
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


public class ModificarSolicitudTask
  implements JavaDelegate
{
     public void execute(DelegateExecution execution){
      Service modificar= new Service();
      try{
      ModificarSolicitud solicitud = new ModificarSolicitud();
      String idStatus=((String) execution.getVariable("idEstatus"));
      int numEstatus = Integer.parseInt(idStatus.trim());
      solicitud.setIdEstatus(numEstatus);
      solicitud.setIdSolicitud((int)execution.getVariable("idSolicitud"));
      int resp=modificar.ModificarSolicitud(solicitud);
      }
      catch(Exception e){

      }
       
    }
}