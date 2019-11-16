package gt.gob.sat.expel.microservicio_node_alfresco.controllers;

import gt.gob.sat.expel.microservicio_node_alfresco.services.ClaseService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import gt.gob.sat.expel.microservicio_node_alfresco.dto.NodeRefDto;
import gt.gob.sat.expel.microservicio_node_alfresco.dto.ParamNodeDocDto;
import gt.gob.sat.expel.microservicio_node_alfresco.dto.ParamDocDto;

@Api
@RestController
public class MicroServicioController {

	final static Logger logger = LoggerFactory.getLogger(MicroServicioController.class);
	
	@Autowired
    private ClaseService service;
	
	
        @PostMapping("/nodeIdDocto")    
    public NodeRefDto obtenerNodeId(@RequestBody ParamDocDto parametros){
                ParamNodeDocDto param = new ParamNodeDocDto();
                param.setExpediente(parametros.getExpediente());
                param.setDocto(parametros.getDocto());
                String res = service.obtenerNodeRefDocto(param);
                NodeRefDto resultado = new NodeRefDto();
                resultado.setNodeRef(res);
                return resultado;
            }
}
