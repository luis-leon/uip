package gt.gob.sat.expel.task.service;

import gt.gob.sat.expel.task.dto.EnvioSolicitudDto;
import gt.gob.sat.expel.task.dto.GetFileDto;
import gt.gob.sat.expel.task.dto.GetNodeIdDto;
import java.util.Base64;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import gt.gob.sat.expel.task.dto.ModificarSolicitud;
import gt.gob.sat.expel.task.dto.NodeRefDto;
import gt.gob.sat.expel.task.dto.SharedLinkEntry;
import org.springframework.http.HttpEntity;
import org.springframework.web.util.UriComponentsBuilder;



public class Service
{
    public Integer GuardarSolicitud(EnvioSolicitudDto param) {
        int respuesta=0;
           try {
              
               RestTemplate restTemplate = new RestTemplate();
               HttpHeaders headers = new HttpHeaders();
               headers.add("Accept", "application/json;charset=UTF-8");
               headers.setContentType(MediaType.APPLICATION_JSON);
               HttpEntity<?> requestBody = new HttpEntity<>(param, headers);
               String ruta="https://rtu.desa.sat.gob.gt/api/service_solicituduip/solicitud";
               respuesta=restTemplate.postForObject(ruta,requestBody,Integer.class);
           } catch (RestClientException e) {
               respuesta = 0;
               //("Error-> al consultar metodo consultarDeclaracionesPosteriores." + e.getMessage());
           }
           return respuesta;
       }
    
    public SharedLinkEntry ObtenerShareId(GetFileDto param, String rutaAcs, String authStringEnc) {
        SharedLinkEntry respuesta = new SharedLinkEntry();
           try {
              
               RestTemplate restTemplate = new RestTemplate();
               HttpHeaders headers = new HttpHeaders();
               headers.add("Accept", "application/json;charset=UTF-8");
               headers.add("Authorization", "Basic " + authStringEnc);
               headers.setContentType(MediaType.APPLICATION_JSON);
               HttpEntity<?> requestBody = new HttpEntity<>(param, headers);
               String ruta= rutaAcs;
               respuesta=restTemplate.postForObject(ruta,requestBody, SharedLinkEntry.class);
           } catch (RestClientException e) {
               respuesta = null;
               //("Error-> al consultar metodo consultarDeclaracionesPosteriores." + e.getMessage());
           }
           return respuesta;
       }
    
        public NodeRefDto ObtenerNodeRef(GetNodeIdDto param, String rutaMicro) {
        NodeRefDto respuesta = new NodeRefDto();
           try {
              
               RestTemplate restTemplate = new RestTemplate();
               HttpHeaders headers = new HttpHeaders();
               headers.add("Accept", "application/json;charset=UTF-8");
               headers.setContentType(MediaType.APPLICATION_JSON);
               HttpEntity<?> requestBody = new HttpEntity<>(param, headers);
               String ruta= rutaMicro;
               respuesta=restTemplate.postForObject(ruta,requestBody, NodeRefDto.class);
           } catch (RestClientException e) {
               respuesta = null;
               //("Error-> al consultar metodo consultarDeclaracionesPosteriores." + e.getMessage());
           }
           return respuesta;
       }
        
    public Integer ModificarSolicitud(ModificarSolicitud param) {
        int respuesta=0;
           try {
            
            String ruta="https://rtu.desa.sat.gob.gt/api/service_solicituduip/solicitud";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", "application/json;charset=UTF-8");
            HttpEntity<String> entity = new HttpEntity<>(headers);
               headers.add("Accept", "application/json;charset=UTF-8");
               
            Map < String, Integer > params = new HashMap < String, Integer > ();
            params.put("idSolicitud", param.getIdSolicitud());
            params.put("idEstatus", param.getIdEstatus());

            ModificarSolicitud updatedSolicitud = new ModificarSolicitud();
            updatedSolicitud.setIdEstatus(param.getIdEstatus());
            updatedSolicitud.setIdSolicitud(param.getIdSolicitud());
            restTemplate.put(ruta, updatedSolicitud, params);
            respuesta=1;
             } catch (RestClientException e) {
               respuesta = 0;
               //("Error-> al consultar metodo consultarDeclaracionesPosteriores." + e.getMessage());
           }
           return respuesta;
       }
    
}


