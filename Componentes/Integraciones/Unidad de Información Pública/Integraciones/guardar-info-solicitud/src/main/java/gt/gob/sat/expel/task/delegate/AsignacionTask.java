package gt.gob.sat.expel.task.delegate;

import gt.gob.sat.expel.task.dto.DatosApi;
import gt.gob.sat.expel.task.dto.DatosAsignacion;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsignacionTask
  implements JavaDelegate
{
    final static Logger logger = LoggerFactory.getLogger(AsignacionTask.class);
    public void execute(DelegateExecution execution){
       RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers1 = new HttpHeaders();
        String urlBaseAsignacion =(String) execution.getVariable("UrlBase");
        String categoria = (String) execution.getVariable("categoriaAsignacion");
        String urlAsignacion= urlBaseAsignacion+categoria;
        logger.info("urlAsignacion---- " + urlAsignacion);
        UriComponentsBuilder builder1 = UriComponentsBuilder.fromHttpUrl(urlAsignacion);
            
            headers1.add("Authorization", "Basic ");
            HttpEntity<String> entity1 = new HttpEntity<>(headers1);
            ResponseEntity<DatosAsignacion> res1 = restTemplate.exchange(builder1.toUriString(), HttpMethod.GET, entity1, DatosAsignacion.class);    

            execution.setVariable("nombreUsuario", res1.getBody().getNombreUsuario());
            execution.setVariable("paraCorreo", res1.getBody().getParaCorreo());

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            String userAps=(String) execution.getVariable("userAuth");
            String passAps = (String) execution.getVariable("passAuth");
            logger.info("credenciales---- " + userAps+ ":" + passAps);
            String authString =  userAps+ ":" + passAps;
            String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
            String urlAmbienteAps=(String) execution.getVariable("urlAps");
            logger.info("urlAmbiente---- " + urlAmbienteAps);
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlAmbienteAps)
                    .queryParam("email", res1.getBody().getParaCorreo());
            headers.add("Authorization", "Basic " + authStringEnc);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<DatosApi> res = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, DatosApi.class);
            //se envia el id de usuario
            execution.setVariable("idUsuario", res.getBody().data.get(0).id);
    }
}