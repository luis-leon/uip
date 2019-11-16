package gt.gob.sat.uip.service_asignacionesrevisores.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import gt.gob.sat.uip.service_asignacionesrevisores.model.CatRevisores;
import gt.gob.sat.uip.service_asignacionesrevisores.model.ColaboradorRevisor;
import gt.gob.sat.uip.service_asignacionesrevisores.model.User;
import gt.gob.sat.uip.service_asignacionesrevisores.repositories.CatRevisoresRepository;
import gt.gob.sat.uip.service_asignacionesrevisores.repositories.ColaboradorRevisorRepository;

import gt.gob.sat.uip.service_asignacionesrevisores.repositories.GeneralRepository;
import gt.gob.sat.uip.service_asignacionesrevisores.repositories.UserPageRepository;
import gt.gob.sat.uip.service_asignacionesrevisores.repositories.UserRepository;

@Service
@ConfigurationProperties(prefix = "sat.arquetipo-service3")
public class ClaseService {

	final static Logger logger = LoggerFactory.getLogger(ClaseService.class);
	
	private String pingUrl;

    @Autowired
    protected RestTemplate restTemplate;
	
	@Autowired
	private GeneralRepository generalRepository;
	
	@Autowired
	private UserRepository userRepository;
        
        @Autowired
	private ColaboradorRevisorRepository colaboradorRevisorRepository;
        
        @Autowired
	private CatRevisoresRepository catRevisoresRepository;

	@Autowired
	UserPageRepository userPageRepository;
	
	/**
	 * Realiza busqueda de una tabla por el id del objeto
	 * @return Objeto con los datos requeridos sino los devuelve vacios
	 */
	public User obtenerUsuario(Integer id) {
		Optional<User> user = userRepository.findById(id);
		logger.debug("Obteniendo datos de usuario");
		return user.orElse(new User());
	}
        
          public CatRevisores obtenerCatCategoriasId(Integer id) {
		//List<CatRevisores> catRevisores = generalRepository.obtenerCatRevisores();
                Optional<CatRevisores> catRevisores = catRevisoresRepository.findById(id);
		logger.debug("Obteniendo datos de todos las categorias ");
		return catRevisores.orElse(new CatRevisores() );
	}
        public List<ColaboradorRevisor> obtenerColaboradoresCategoria(Integer id_categoria) {
		//List<CatRevisores> catRevisores = generalRepository.obtenerCatRevisores();
                List<ColaboradorRevisor> colaboradores = generalRepository.obtenerColaboradoresCategoria(id_categoria);
		logger.debug("Obteniendo datos de todos los colaboradores por categorias ");
		return colaboradores;
	}
          



	/**
	 * Realiza una busqueda de todos los objetos de cada clase
	 * @return lista con objeto  conteniendo informacion del objeto solicitado
	 */
	public List<User> obtenerUsuarios() {
		List<User> users = userRepository.findAll();
		logger.debug("Obteniendo datos de todos los usuarios");
		return users;
	}
              
        public List<CatRevisores> obtenerCatCategorias() {
		//List<CatRevisores> catRevisores = generalRepository.obtenerCatRevisores();
                List<CatRevisores> catRevisores = catRevisoresRepository.findAll();
		logger.debug("Obteniendo datos de todos las categorias ");
		return catRevisores;
	}
        
        public List<ColaboradorRevisor> obtenerColaboradores() {
		//List<CatRevisores> catRevisores = generalRepository.obtenerCatRevisores();
                List<ColaboradorRevisor> colaboradores = colaboradorRevisorRepository.findAll();
		logger.debug("Obteniendo datos de todos las categorias ");
		return colaboradores;
	}


	/**
	 * Crea un nuevo usuario
	 * @param Objeto tipo User
	 * @return Objeto User con los valores que fueron ingresados
	 */
	public User crearUsuario(User user) {
		logger.debug("Creando usuario");
		return userRepository.save(user);
	}
        
        public CatRevisores crearCategoriaRevisor(CatRevisores categoria) {
		logger.debug("Creando categoria revisor");
		return catRevisoresRepository.save(categoria);
	}

	/**
	 * Elimina el registro del usuario
	 * @param id
	 * @return
	 */
	public void eliminarUsuario(Integer id) {
		logger.debug("Eliminando usuario");
		userRepository.deleteById(id);
	}

	
	/**
	 * Busca un usuario utilizando el servicio externo definido en la variable sat.arquetipo-service3.pingUrl
	 * @param min valor minimo que se puede obtener al generar el valor aleatorio
	 * @param max valor maximo que se puede obtener al generar el valor aleatorio
	 * @return Objeto Usuario conteniendo informacion de un usuario con id comprendido entre el valor minimo y maximo
	 */
	@HystrixCommand(fallbackMethod = "retrieveFallback")
    public User consumirOtroMicroservicio(Integer min, Integer max) {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt((max - min) + 1) + min;
        User usuario = restTemplate.getForObject(pingUrl + randomInt, User.class);
        logger.debug("Se consumio otro microservicio");
		return usuario;
    }

	/**
	 * Metodo utilizado en caso de fallo
	 * @param min
	 * @param max
	 * @return Objetos User sin valores
	 */
	public User retrieveFallback(Integer min, Integer max) {
        User user = new User();
        user.setName("No retorna datos");
        logger.debug("Utilizando hystrix en caso de fallos en consumirOtroMicroservicio");
        return user;
    }
        
        /**
	 * Realiza una busqueda de todos los usuarios indicando la longitud de la pagina
	 * @return lista con objeto User conteniendo informacion de usuarios
	 */
	public Page<User> obtenerUsuariosByPage(Pageable pageable) {
		logger.debug("Obteniendo datos de todos los usuarios por pagina");
		return userPageRepository.findAll(pageable);
	}
}
