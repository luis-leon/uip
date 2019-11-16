/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.expel.asignacion.revisor;
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
/**
 *
 * @author amolopezo
 */
public class AsignacionRevisorTask implements JavaDelegate{        
    public void execute(DelegateExecution execution){
        RestTemplate restTemplate = new RestTemplate();
        DatosAsignacion respuesta = restTemplate.getForObject("https://rtu.desa.sat.gob.gt/api/service_asignaciones/asignacion-revisor?categoria=1", DatosAsignacion.class);
            execution.setVariable("nombreUsuario", respuesta.getNombreUsuario());
            execution.setVariable("paraCorreo", respuesta.getParaCorreo());

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            String authString =  "admin@app.activiti.com" + ":" + "admin";
            String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://alfresco.desa.sat.gob.gt/activiti-app/api/enterprise/users?")
                    .queryParam("email", respuesta.getParaCorreo());
            headers.add("Authorization", "Basic " + authStringEnc);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<DatosApi> res = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, DatosApi.class);
    
            execution.setVariable("idUsuario", res.getBody().data.get(0).id); 
    }
    
}
