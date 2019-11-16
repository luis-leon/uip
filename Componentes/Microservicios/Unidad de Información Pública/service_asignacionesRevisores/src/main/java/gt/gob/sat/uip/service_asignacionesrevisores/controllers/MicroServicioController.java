package gt.gob.sat.uip.service_asignacionesrevisores.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import gt.gob.sat.arquitectura.microservices.exceptions.ResourceNotFoundException;
import gt.gob.sat.arquitectura.microservices.util.FileStorageService;
import gt.gob.sat.uip.service_asignacionesrevisores.model.CatRevisores;
import gt.gob.sat.uip.service_asignacionesrevisores.model.ColaboradorRevisor;
import gt.gob.sat.uip.service_asignacionesrevisores.model.User;
import gt.gob.sat.uip.service_asignacionesrevisores.model.colaboradorAsignado;
import gt.gob.sat.uip.service_asignacionesrevisores.params.Categoria;

import gt.gob.sat.uip.service_asignacionesrevisores.services.ClaseService;
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
	/**
         * Rutas utilizadas para obtener los objetos de las clases especificados en el path
        **/
	
	//-------------
        @GetMapping("/detalles-categoriaId")
	@ApiOperation(value="obtener  categoria",notes="Retorna categorias")
        public CatRevisores obtenerColaborador(Categoria params) {
                    int par= params.getCategoria();
                    CatRevisores categorias = claseService.obtenerCatCategoriasId(par);
            return categorias;
        }
        
        @GetMapping("/colaboradores-categoriaId")
	@ApiOperation(value="obtener  categoria",notes="Retorna categorias")
        public List<ColaboradorRevisor> obtenerColaboradorCategoria(Categoria params) {
                    List<ColaboradorRevisor> categorias = claseService.obtenerColaboradoresCategoria(params.getCategoria());
            return categorias;
        }
        
        @GetMapping("/asignacion-revisor")
	@ApiOperation(value="obtener  categoria",notes="Retorna categorias")
        public colaboradorAsignado obtenerAsignacionRevisor(Categoria params) {
                    colaboradorAsignado asignado= new colaboradorAsignado();
                    colaboradorAsignado primeroLista= new colaboradorAsignado();
                    int id_asignado=0, id_siguiente=0;
                    //listado de colaboradores
                     List<ColaboradorRevisor> colaboradoresCategoria = claseService.obtenerColaboradoresCategoria(params.getCategoria());
                     int par= params.getCategoria();
                     //la categoria requerida en el parametros
                     CatRevisores detalleCategoria = claseService.obtenerCatCategoriasId(par);
                    
                     if (detalleCategoria.getUltimoAsignado()!=null)
                     {
                          id_asignado= detalleCategoria.getUltimoAsignado();
                          id_siguiente= id_asignado+1;
                          
                            if(id_siguiente>colaboradoresCategoria.size())
                            {
                            id_siguiente=1;
                            }
                     }
                     else
                     {
                         id_siguiente=1;
                     }
                     
                     if(detalleCategoria.getUltimoAsignado()!= null)
                     {
                         for (int i=0; i<colaboradoresCategoria.size();i++)
                         {
                                                    
                             if(colaboradoresCategoria.get(i).getIdRevisor()==id_siguiente)
                             {
                                 asignado.setNombreUsuario(colaboradoresCategoria.get(i).getNombre());
                                 asignado.setParaCorreo(colaboradoresCategoria.get(i).getCorreo()); 
                                 CatRevisores revisor = new CatRevisores();
                                 revisor.setDescripcionCargo(detalleCategoria.getDescripcionCargo());
                                 revisor.setIdCat(detalleCategoria.getIdCat());
                                 revisor.setUltimoAsignado(id_siguiente);
                                 CatRevisores newCategoria = claseService.crearCategoriaRevisor(revisor);
                                    URI location = ServletUriComponentsBuilder
                                    .fromCurrentRequest().path("/{id}")
                                    .buildAndExpand(newCategoria.getIdCat()).toUri();
                                ResponseEntity.created(location).build();
                                return asignado;
                             }
                         }
                     }
                     else
                     {
                         for(int i=0; i<colaboradoresCategoria.size();i++)
                         {
                             if(colaboradoresCategoria.get(i).getIdRevisor()==1)
                             {
                                 primeroLista.setNombreUsuario(colaboradoresCategoria.get(i).getNombre());
                                 primeroLista.setParaCorreo(colaboradoresCategoria.get(i).getCorreo());
                                 CatRevisores revisor = new CatRevisores();
                                 revisor.setUltimoAsignado(1);
                                 revisor.setIdCat(detalleCategoria.getIdCat());
                                 revisor.setDescripcionCargo(detalleCategoria.getDescripcionCargo());
                                 CatRevisores newCategoria = claseService.crearCategoriaRevisor(revisor);
                                 URI location = ServletUriComponentsBuilder
				 .fromCurrentRequest().path("/{id}")
				 .buildAndExpand(newCategoria.getIdCat()).toUri();
                                //ResponseEntity.created(location).build();
                             }
                         }
                         //actualizar tabla en el caso que el campo este nulo por ser la primera vez que se asigna
                     }
        return primeroLista;
        }
        
        /**
         * Rutas utilizadas para obtener los datos de clases especificado en el parametro
         */
        
	
        //------

        /**
         * Rutas para obtener todos los elementos de una clase
         * @return 
         */
	
        //--
        @GetMapping("/colaboradores")
	@ApiOperation(value="obtener colaboradores",notes="Retorna todos los colaboradores y sus atributos")
        public List<ColaboradorRevisor> obtenerColaboradores() {
                    List<ColaboradorRevisor> colaborador = claseService.obtenerColaboradores();
            return colaborador;
        }
        
        //--
         @GetMapping("/catalogo-revisores")
	@ApiOperation(value="obtener catalogo-revisores",notes="Retorna el catalogo y sus atributos")
        public List<CatRevisores> obtenerCatalogoRevisores() {
                    List<CatRevisores> catalogo = claseService.obtenerCatCategorias();
            return catalogo;
        }

    /**
     * Rutas utilizadas para agregar nuevos objetos a las clases indicadas usando el metodo post
     */
       
      
    /**
     * Rutas utilizadas para actualizar objetos de la clase con el metodo PUT
     */ 
    
   
    
    /**
     * Rutas utilizadas para modificar los objetos de clases utilizando el metodo patch
     * 
     */
     @PatchMapping("/categorias-modificar")
    @ApiOperation(value="actualiza categoria",notes="Actualiza los datos de la categoria usando el metodo PATCH")
    public ResponseEntity<?>  actualizar2Categoria(@RequestBody CatRevisores revisor) {
    	CatRevisores newCategoria = claseService.crearCategoriaRevisor(revisor);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newCategoria.getIdCat()).toUri();
		return ResponseEntity.created(location).build();
    }
    /**
     * Rutas utilizadas para eliminar objetos de las clases indicadas
     */
   
    
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
    
    
   
        
        
         @DeleteMapping("/users/{id}")
    @ApiOperation(value="eliminar usuario",notes="Elimina el registro del usuario especificado en el path")
    public void eliminarUsuario(@PathVariable @ApiParam(value="Identificador del usuario") Integer id) {
    	claseService.eliminarUsuario(id);
    }
    */
}
