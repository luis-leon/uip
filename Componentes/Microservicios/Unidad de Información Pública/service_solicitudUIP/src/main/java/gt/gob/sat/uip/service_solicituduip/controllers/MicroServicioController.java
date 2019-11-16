package gt.gob.sat.uip.service_solicituduip.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import gt.gob.sat.arquitectura.microservices.exceptions.ResourceNotFoundException;
import gt.gob.sat.arquitectura.microservices.util.FileStorageService;
import gt.gob.sat.uip.service_solicitud.Dto.ActualizarDto;
import gt.gob.sat.uip.service_solicitud.Dto.EnvioSolicitudDto;
import gt.gob.sat.uip.service_solicituduip.model.*;
import gt.gob.sat.uip.service_solicituduip.repositories.GeneralRepository;
import gt.gob.sat.uip.service_solicituduip.services.ClaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api
@RestController
public class MicroServicioController {

    final static Logger logger = LoggerFactory.getLogger(MicroServicioController.class);

    @Autowired
    ClaseService claseService;
	
    @Autowired
    private FileStorageService fileStorageService;
	
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private GeneralRepository generalRepository;
    
    @PostMapping("/solicitud")
    @ApiOperation(value="obtener datos de de la solicitud",notes="Obtiene los datos paga guardar la solicitud")
    public int prueba(@RequestBody EnvioSolicitudDto body) {

        PersonaJuridica pJur = new PersonaJuridica();
        Contribuyente pC = new Contribuyente();
        SolicitudUipId solId = new SolicitudUipId();
        SolicitudUip sol = new SolicitudUip();
        DatosContactoId dcI = new DatosContactoId();
        DatosContacto dc = new DatosContacto();
        
        
        PersonaNatural persona = new PersonaNatural();
        if (((body.getPrimerNombre() != "" && body.getPrimerApellido() != "") || (body.getNit() != "" && body.getRazonSocial() != "")) 
                && body.getInformacionSolicitada() != "" && (body.getCorreoelectronico1() != "" || body.getCorreoelectronico2() != ""
                || body.getDireccionFisica() != "" || body.getFax() != ""
                || body.getTelefono1() != "" || body.getTelefono2() != "")) {
            
            if (body.getPrimerNombre() != "" && body.getPrimerApellido() != "") {
                persona.setIdPersonaNatural(generalRepository.obtenerSecPersonaNatural().get(0));
                persona.setPrimerNombre(body.getPrimerNombre());
                persona.setSegundoNombre((body.getSegundoNombre() != "")?body.getSegundoNombre():null);
                persona.setTercerNombre((body.getTercerNombre() != "")?body.getTercerNombre():null);
                persona.setPrimerApellido(body.getPrimerApellido());
                persona.setSegundoApellido((body.getSegundoApellido() != "")?body.getSegundoApellido():null);
                persona.setTercerApellido((body.getTercerApellido() != "")?body.getTercerApellido():null);
                persona.setIdTipoIdentificacionOficial((body.getTipoIdentificacionPersonal() == 0)?body.getTipoIdentificacionPersonal():null);
                persona.setNumeroIdentificacion((body.getNumeroIdentificacion() != "")?body.getNumeroIdentificacion():null);

                persona = claseService.crearPersonaNatural(persona); 
            }
            
            if (body.getNit() != "" && body.getRazonSocial() != "") {
                pC.setIdContribuyente(generalRepository.obtenerSecContri().get(0));
                pC.setNit(body.getRazonSocial());
                pC.setIdTipoContribuyente(1);
                pC = claseService.crearContribuyente(pC);
                pJur.setIdContribuyente(pC.getIdContribuyente());
                pJur.setRazonSocial(body.getRazonSocial());
                pJur.setVencimientoRepresentacion(body.getVencimientoRepresentacionLegal());
                pJur = claseService.crearPersonaJuridica(pJur);
            }
            
                solId.setIdSolicitud(generalRepository.obtenerSecSolicitud().get(0).intValue());
                solId.setNumeroSolicitud(body.getNumeroSolicitud());
                
                if (body.getNit() != "" && body.getRazonSocial() != "") {
                    solId.setIdPersonaJuridica(pJur.getIdContribuyente().intValue());
                } else {
                    solId.setIdPersonaJuridica(null);
                }
                if (body.getPrimerNombre() != "" && body.getPrimerApellido() != "") {
                    solId.setIdPersonaNatural(persona.getIdPersonaNatural().intValue());
                } else {
                    solId.setIdPersonaNatural(null);
                }
                
                solId.setInformacionSolicitada(body.getInformacionSolicitada());
                solId.setInformacionAdicionalSolicitada(body.getInformacionAdicionalSolicitada());
                solId.setIdEstatus(body.getEstatus());
                sol.setId(solId);
                sol = claseService.crearSolicitud(sol);
            
            if(body.getCorreoelectronico1() != "") {
                dcI.setIdSolicitud(solId.getIdSolicitud());
                dcI.setIdTipoContacto(1);
                dcI.setDetalleContacto(body.getCorreoelectronico1());
                dc.setId(dcI);
                claseService.crearDatosContacto(dc);
            }
            
            if(body.getCorreoelectronico2() != "") {
                System.out.println(body.getCorreoelectronico2());
                dcI.setIdSolicitud(solId.getIdSolicitud());
                dcI.setIdTipoContacto(4);
                dcI.setDetalleContacto(body.getCorreoelectronico2());
                dc.setId(dcI);
                claseService.crearDatosContacto(dc);
            }
            
            if(body.getTelefono1() != "") {
                dcI.setIdSolicitud(solId.getIdSolicitud());
                dcI.setIdTipoContacto(2);
                dcI.setDetalleContacto(body.getTelefono1());
                dc.setId(dcI);
                claseService.crearDatosContacto(dc);
            }
            
            if(body.getTelefono2() != "") {
                dcI.setIdSolicitud(solId.getIdSolicitud());
                dcI.setIdTipoContacto(3);
                dcI.setDetalleContacto(body.getTelefono2());
                dc.setId(dcI);
                claseService.crearDatosContacto(dc);
            }
            
            if(body.getFax() != "") {
                dcI.setIdSolicitud(solId.getIdSolicitud());
                dcI.setIdTipoContacto(6);
                dcI.setDetalleContacto(body.getFax());
                dc.setId(dcI);
                claseService.crearDatosContacto(dc);
            }
            
            if(body.getDireccionFisica() != "") {
                dcI.setIdSolicitud(solId.getIdSolicitud());
                dcI.setIdTipoContacto(6);
                dcI.setDetalleContacto(body.getDireccionFisica());
                dc.setId(dcI);
                claseService.crearDatosContacto(dc);
            }
            return solId.getIdSolicitud();
        }
        
	return 0;
    }
    
    /*@GetMapping("/solicitudes/{id}")
    @ApiOperation(value="obtener datos de la solicitud",notes="Obtiene los datos de la especificado en el path")
    public SolicitudUip obtenerSolicitud(@RequestHeader(name="Accept-Language", required=false) Locale locale, 
    		@PathVariable @ApiParam(value="Identificador de la solicitud") Integer id) {
		SolicitudUip solicitud = claseService.obtenerSolicitud(id);
		if (solicitud==null || solicitud.getId()==0)
		{
			logger.info("No se encuentra datos de la solicitud");
			throw new ResourceNotFoundException(messageSource.getMessage("solicitud.noexiste.message",null,locale),
					messageSource.getMessage("developer.solicitud.noexiste.message",null,locale),
					1201, "http://mydomain.sat.gob.gt");
		}
		logger.info("Se obtienen datos de la solicitud");
        return solicitud;
    }
    
    @GetMapping(value="/solicitud", params={"id_solicitud"})
    @ApiOperation(value="obtener datos de la solicitud",notes="Obtiene los datos de la solicitud especificado en el parametro")
    public SolicitudUip obtenerSolicitudParam(@RequestParam @ApiParam(value="Identificador de la solicitud") Integer id_solicitud) {
		SolicitudUip solicitud = claseService.obtenerSolicitud(id_solicitud);
		if (solicitud==null || solicitud.getIdSolicitud()==0)
		{
			throw new ResourceNotFoundException("La solicitud no existe","Resultado de la busqueda fue nulo", 1201, "http://mydomain.sat.gob.gt");
		}
        return solicitud;
    }
    
    @GetMapping("/solicitudes")
    @ApiOperation(value="obtener solicitudes",notes="Retorna todos las solicitudes y sus atributos")
    public List<SolicitudUip> obtenerSolicitudes() {
		List<SolicitudUip> solicitudes = claseService.obtenerSolicitudes();
        return solicitudes;
    }*/
    
    /*@PostMapping("/solicitudes")
    @ApiOperation(value="crea solicitud",notes="Agrega una solicitud")
    public Resource<SolicitudUip> crearSolicitud(@RequestBody SolicitudUip solicitud) {
    	SolicitudUip newSolicitud = claseService.crearSolicitud(solicitud);
    	Resource<SolicitudUip> resource = new  Resource<SolicitudUip>(solicitud);
        // link = linkTo(methodOn(this.getClass()).obtenerSolicitud(null, newSolicitud.getId())).withSelfRel();
        link = linkTo(methodOn(this.getClass()).obtenerSolicitud(null, newSolicitud.())).withSelfRel();
    	resource.add(link);
		return resource;
    }*/
    
    @PutMapping("/solicitud")
    @ApiOperation(value="actualiza solicitud",notes="Actualiza los datos de la solicitud usando el metodo PUT")
    public String  actualizarSolicitud(@RequestBody ActualizarDto solicitud) {
    	generalRepository.actualizarSolicitud(solicitud.getIdSolicitud(), solicitud.getIdEstatus());
        return "Actualizado";
    }
    
    /*@PatchMapping("/solicitud")
    @ApiOperation(value="actualiza solicitud",notes="Actualiza los datos de la solicitud usando el metodo PATCH")
    public ResponseEntity<?>  actualizar2Solicitud(@RequestBody SolicitudUip solicitud) {
    	SolicitudUip newSolicitud = claseService.crearSolicitud(solicitud);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newSolicitud.getId()).toUri();
		return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping("/solicitudes/{id}")
    @ApiOperation(value="eliminar solicitud",notes="Elimina el registro de la solicitud especificada en el path")
    public void eliminarSolicitud(@PathVariable @ApiParam(value="Identificador de la solicitud") Integer id_solicitud) {
    	claseService.eliminarSolicitud(id_solicitud);
    }
    
    @GetMapping(value = "/solicitudesPages")
    @ApiOperation(value="obtener solicitudes",notes="Retorna todas las solicitudes y sus atributos por pagina")
    Page<SolicitudUip> solicitudesPageable(Pageable pageable) {
            return claseService.obtenerSolicitudesByPage(pageable);
    }*/
    
}
