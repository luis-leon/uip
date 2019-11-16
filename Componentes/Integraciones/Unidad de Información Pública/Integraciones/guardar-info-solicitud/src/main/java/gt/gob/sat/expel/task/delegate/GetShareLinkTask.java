/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.expel.task.delegate;

import static gt.gob.sat.expel.task.delegate.AsignacionTask.logger;
import gt.gob.sat.expel.task.dto.DatosApi;
import gt.gob.sat.expel.task.dto.DatosAsignacion;
import gt.gob.sat.expel.task.dto.GetFileDto;
import gt.gob.sat.expel.task.dto.GetNodeIdDto;
import gt.gob.sat.expel.task.dto.NodeRefDto;
import gt.gob.sat.expel.task.dto.SharedLink;
import gt.gob.sat.expel.task.dto.SharedLinkEntry;
import gt.gob.sat.expel.task.service.Service;
import java.util.Base64;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author amolopezo
 */
public class GetShareLinkTask 
      implements JavaDelegate
{
    final static Logger logger = LoggerFactory.getLogger(AsignacionTask.class);
    public void execute(DelegateExecution execution){
       Service servicio = new Service();
       String nodeIdEnviar;
       NodeRefDto respNode = new NodeRefDto();
       GetNodeIdDto getNodeid= new GetNodeIdDto();
       String urlBaseNodeId =(String) execution.getVariable("urlNodeId");
        String docto = (String) execution.getVariable("docto");
        String expediente = (String) execution.getVariable("expediente");
        getNodeid.setDocto(docto);
        getNodeid.setExpediente(expediente);
        try{
        respNode=servicio.ObtenerNodeRef(getNodeid, urlBaseNodeId);
        nodeIdEnviar= respNode.getNodeRef();
        }
        catch (Exception e){
        nodeIdEnviar= null;
        logger.error("eror---- " + e);
        }
        
        logger.info("nodeID---- " + nodeIdEnviar);
        /*
       servicio.ObtenerNodeRef(null, null)
       RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers1 = new HttpHeaders();
        
        logger.info("urlAsignacion---- " + urlBaseNodeId);
        UriComponentsBuilder builder1 = UriComponentsBuilder.fromHttpUrl(urlBaseNodeId)
                .queryParam("docto", docto)
                .queryParam("expediente", expediente);
            headers1.add("Authorization", "Basic ");
            HttpEntity<String> entity1 = new HttpEntity<>(headers1);
            ResponseEntity<NodeRefDto> res1 = restTemplate.exchange(builder1.toUriString(), HttpMethod.POST, entity1, NodeRefDto.class);    
*/
                        
            String expiresAt = (String) execution.getVariable("expiresAt");
            String urlAmbienteAcs=(String) execution.getVariable("urlAcs");
            String userAcs=(String) execution.getVariable("userAuthAcs");
            String passAcs = (String) execution.getVariable("passAuthAcs");
             String authString =  userAcs+ ":" + passAcs;
            String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
            String nodeId = nodeIdEnviar;
            GetFileDto file = new GetFileDto();
            file.setExpiresAt(expiresAt);
            file.setNodeId(nodeId);
            
            SharedLinkEntry res = new SharedLinkEntry();
            String shareId;
            try{
                res = servicio.ObtenerShareId(file, urlAmbienteAcs, authStringEnc);
                shareId = res.getEntry().getId();
            }
            catch(Exception e){
                logger.error("eror---- " + e);
                shareId = null;
            }
        logger.info("sharedid---- " + shareId);
            execution.setVariable("shareId", shareId);
    }
    
}
