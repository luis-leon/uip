package gt.gob.sat.microservicio_solicitudesuip.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import gt.gob.sat.arquitectura.microservices.exceptions.ResourceNotFoundException;
import gt.gob.sat.arquitectura.microservices.util.FileStorageService;
import gt.gob.sat.microservicio_solicitudesuip.model.SolicitudDevolucionIva;
import gt.gob.sat.microservicio_solicitudesuip.model.User;
import gt.gob.sat.microservicio_solicitudesuip.model.reqEstatus;
import gt.gob.sat.microservicio_solicitudesuip.model.resEstatus;
import gt.gob.sat.microservicio_solicitudesuip.model.respuestaSolicitudes;
import gt.gob.sat.microservicio_solicitudesuip.model.updEstatus;
import gt.gob.sat.microservicio_solicitudesuip.services.ClaseService;
import gt.gob.sat.microservicio_solicitudesuip.services.solicitudesService;
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
    solicitudesService claseService;
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@Autowired
	private MessageSource messageSource;
    private Object generalRepository;
        
        //Obtiene todas las solicitudes
        
        @GetMapping("/solicitudes")
	@ApiOperation(value="obtener todas las solicitudes que actualmente estan en base de datos",notes="Retorna todos las solicitudes y sus atributos")
    public List<SolicitudDevolucionIva> obtenerUsuarios() {
		List<SolicitudDevolucionIva> solicitudes = claseService.obtenerUsuarios();
        return solicitudes;
    }
    //
      @GetMapping("/estatus-solicitud")
	@ApiOperation(value="se obtiene un valor booleano si existe alguna solicitud para ese nit año y periodo",notes="Retorna todos las solicitudes y sus atributos")
    public resEstatus obtenerEstatusSolicitud(reqEstatus param) {
                resEstatus solicitudes= claseService.obtieneSolicitudVigente(param);
        return solicitudes;
    }
    
     @GetMapping("/obtener-datos-solicitud")
	@ApiOperation(value="obtiene las solicitudes con estatus --por revisar-- y --asignada tentativamente--",notes="Retorna todos las solicitudes y sus atributos")
    public respuestaSolicitudes obtenerSolicitud(reqEstatus param) {
                respuestaSolicitudes solicitudes= claseService.obtieneSolicitudes(param);
        return solicitudes;
    }
    /*
    @GetMapping("/solicitudes/{id}")
	@ApiOperation(value="obtener datos de solicitudes",notes="Obtiene los datos del solicitudes especificado en el path")
    public SolicitudDevolucionIva obtenerSolicitud (@RequestHeader(name="Accept-Language", required=false) Locale locale, 
    		@PathVariable @ApiParam(value="Identificador de solicitud") Integer id) {
		SolicitudDevolucionIva solicitud = claseService.obtenerSolicitud(id);
		if (solicitud==null || solicitud.getIdSolicitudIva()==0)
		{
		//logger.info("No se encuentra datos de solicitud");
		//	throw new ResourceNotFoundException(messageSource.getMessage("solicitud.noexiste.message",null,locale),
		//			messageSource.getMessage("developer.solicitud.noexiste.message",null,locale),
		//			1201, "http://mydomain.sat.gob.gt");
		}
		logger.info("Se obtienen datos de solicitud");
        return solicitud;
    }    */
    @PostMapping("/crear-solicitudes")
    @ApiOperation(value="crea solicitudes",notes="Agrega una nueva solicitud a base de datos")
    public SolicitudDevolucionIva crearSolicitud (@RequestBody SolicitudDevolucionIva solicitud) {
        SolicitudDevolucionIva newSolicitud = new SolicitudDevolucionIva();
        try{
            newSolicitud = claseService.crearSolicitud(solicitud);
        }
        catch (Exception e)
        {
            newSolicitud = null;
        }
    	
		return newSolicitud;
    }
    
    @PutMapping("/modificar-estatus-solicitud")
    @ApiOperation(value="modifica el campo status",notes="Actualiza los datos de solicitud usando el metodo PUT")
    public ResponseEntity<?>  actualizarSolicitud(@RequestBody SolicitudDevolucionIva solicitud) {
    	SolicitudDevolucionIva newSolicitud= new SolicitudDevolucionIva();
        ResponseEntity<?> resp;
        try{
        newSolicitud = claseService.crearSolicitud(solicitud);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newSolicitud.getIdSolicitudIva()).toUri();
                resp=ResponseEntity.created(location).build();
        }
        catch(Exception e)
        {
                resp=null;
        }
                
		return resp;
    }
    
    @PatchMapping("/modificar-estatus-solicitud")
    @ApiOperation(value="modifica el campo status",notes="Actualiza los datos de solicitud usando el metodo PATCH")
    public ResponseEntity<?>  actualizar2Usuario(@RequestBody updEstatus solicitud) {
    	SolicitudDevolucionIva newSolicitudtemp = new SolicitudDevolucionIva();
        //SolicitudDevolucionIva newSolicitud = new SolicitudDevolucionIva();
        
        
        try{
            newSolicitudtemp=claseService.obtenerSolicitudUpd(solicitud);
                int estatus=solicitud.getIdEstatus();
                newSolicitudtemp.setIdEstatus(estatus);
        SolicitudDevolucionIva newSolicitud = claseService.crearSolicitud(newSolicitudtemp);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newSolicitud.getIdSolicitudIva()).toUri();
		return ResponseEntity.created(location).build();
        }
        catch (Exception e){
            return null;
        }
                
    }
    
    /*
	
	@GetMapping("/users/{id}")
	@ApiOperation(value="obtener datos de usuario",notes="Obtiene los datos del usuario especificado en el path")
    public User obtenerUsuario(@RequestHeader(name="Accept-Language", required=false) Locale locale, 
    		@PathVariable @ApiParam(value="Identificador del usuario") Integer id) {
		User user = claseService.obtenerUsuario(id);
		if (user==null || user.getId()==0)
		{
			logger.info("No se encuentra datos de usuario");
			throw new ResourceNotFoundException(messageSource.getMessage("usuario.noexiste.message",null,locale),
					messageSource.getMessage("developer.usuario.noexiste.message",null,locale),
					1201, "http://mydomain.sat.gob.gt");
		}
		logger.info("Se obtienen datos de usuario");
        return user;
    }
	
	@GetMapping(value="/users", params={"id"})
	@ApiOperation(value="obtener datos de usuario",notes="Obtiene los datos del usuario especificado en el parametro")
    public User obtenerUsuarioParam(@RequestParam @ApiParam(value="Identificador del usuario") Integer id) {
		User user = claseService.obtenerUsuario(id);
		if (user==null || user.getId()==0)
		{
			throw new ResourceNotFoundException("El usuario no existe","Resultado de la busqueda fue nulo", 1201, "http://mydomain.sat.gob.gt");
		}
        return user;
    }
	
	@GetMapping("/users")
	@ApiOperation(value="obtener usuarios",notes="Retorna todos los usuarios y sus atributos")
    public List<User> obtenerUsuarios() {
		List<User> users = claseService.obtenerUsuarios();
        return users;
    }

    @PostMapping("/users")
    @ApiOperation(value="crea usuario",notes="Agrega un usuario")
    public Resource<User> crearUsuario(@RequestBody User user) {
    	User newUser = claseService.crearUsuario(user);
    	Resource<User> resource = new  Resource<User>(user);
    	Link link = linkTo(methodOn(this.getClass()).obtenerUsuario(null, newUser.getId())).withSelfRel();
    	resource.add(link);
		return resource;
    }
    
    @PutMapping("/users")
    @ApiOperation(value="actualiza usuario",notes="Actualiza los datos del usuario usando el metodo PUT")
    public ResponseEntity<?>  actualizarUsuario(@RequestBody User user) {
    	User newUser = claseService.crearUsuario(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).build();
    }
    
    @PatchMapping("/users")
    @ApiOperation(value="actualiza usuario",notes="Actualiza los datos del usuario usando el metodo PATCH")
    public ResponseEntity<?>  actualizar2Usuario(@RequestBody User user) {
    	User newUser = claseService.crearUsuario(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping("/users/{id}")
    @ApiOperation(value="eliminar usuario",notes="Elimina el registro del usuario especificado en el path")
    public void eliminarUsuario(@PathVariable @ApiParam(value="Identificador del usuario") Integer id) {
    	claseService.eliminarUsuario(id);
    }
    
    @GetMapping(value = "/usersPages")
    @ApiOperation(value="obtener usuarios",notes="Retorna todos los usuarios y sus atributos por pagina")
	Page<User> employeesPageable(Pageable pageable) {
		return claseService.obtenerUsuariosByPage(pageable);
	}
    
    @PostMapping("/users/{id}/files")
    @ApiOperation(value="Carga de archivo",notes="Permite cargar archivos, retorna la direccion para descarga")
	public String uploadFile (@PathVariable @ApiParam(value="Identificador del usuario") Integer id,
			@RequestParam("file") MultipartFile uploadedFile, RedirectAttributes redirect)
	{
    	redirect.addFlashAttribute("message", "You successfully uploaded " + uploadedFile.getOriginalFilename() + "!");
    	String temp=fileStorageService.storeFile(uploadedFile,null,id.toString());
    	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(temp)
                .toUriString();
    	return fileDownloadUri;
	}
    */
}
