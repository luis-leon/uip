package gt.gob.sat.expel.microservicio_node_alfresco.services;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import gt.gob.sat.expel.microservicio_node_alfresco.repositories.GeneralRepository;
import gt.gob.sat.expel.microservicio_node_alfresco.dto.NodeDto;
import gt.gob.sat.expel.microservicio_node_alfresco.dto.ParamNodeDocDto;
import gt.gob.sat.expel.microservicio_node_alfresco.dto.ParamNodeExpDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;


@Service
@ConfigurationProperties(prefix = "sat.arquetipo-service3")

@Api
@RestController
public class ClaseService {

	final static Logger logger = LoggerFactory.getLogger(ClaseService.class);

    @Autowired
    protected RestTemplate restTemplate;
	
	@Autowired
	private GeneralRepository generalRepository;
    
        public String obtenerNodeRefDocto(ParamNodeDocDto param) {
            NodeDto cont = new NodeDto();   
            List<NodeDto> res = generalRepository.getNodeRefDocto(param.getExpediente(),param.getDocto());
            if(res.isEmpty()) {
                return "No existe ruta";
            }     
            NodeDto objeto = res.get(0);
            String ruta = objeto.getUuid();
        return ruta;
    }
}
