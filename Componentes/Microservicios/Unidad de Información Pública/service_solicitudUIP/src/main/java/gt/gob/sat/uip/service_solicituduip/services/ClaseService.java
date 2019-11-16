package gt.gob.sat.uip.service_solicituduip.services;

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
import gt.gob.sat.uip.service_solicituduip.model.Cita;
import gt.gob.sat.uip.service_solicituduip.model.Contribuyente;
import gt.gob.sat.uip.service_solicituduip.model.DatosContacto;
import gt.gob.sat.uip.service_solicituduip.model.DatosContactoId;
import gt.gob.sat.uip.service_solicituduip.model.Estatus;
import gt.gob.sat.uip.service_solicituduip.model.Pais;
import gt.gob.sat.uip.service_solicituduip.model.PersonaJuridica;
import gt.gob.sat.uip.service_solicituduip.model.PersonaNatural;
import gt.gob.sat.uip.service_solicituduip.model.RazonCita;
import gt.gob.sat.uip.service_solicituduip.model.Resultado;
import gt.gob.sat.uip.service_solicituduip.model.SolicitudUip;
import gt.gob.sat.uip.service_solicituduip.repositories.CitaPageRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.CitaRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.ContribuyentePageRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.ContribuyenteRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.DatosContactoPageRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.DatosContactoRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.EstatusPageRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.EstatusRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.GeneralRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.PaisPageRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.PaisRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.PersonaJuridicaPageRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.PersonaJuridicaRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.PersonaNaturalPageRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.PersonaNaturalRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.RazonCitaPageRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.RazonCitaRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.ResultadoPageRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.ResultadoRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.SolicitudPageRepository;
import gt.gob.sat.uip.service_solicituduip.repositories.SolicitudRepository;

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
    private SolicitudRepository solicitudRepository;

    @Autowired
    SolicitudPageRepository solicitudPageRepository;
    
    @Autowired
    private ResultadoRepository resultadoRepository;

    @Autowired
    ResultadoPageRepository resultadoPageRepository;
    
    @Autowired
    private RazonCitaRepository razonCitaRepository;

    @Autowired
    RazonCitaPageRepository razonCitaPageRepository;
    
    @Autowired
    private PersonaNaturalRepository personaNaturalRepository;

    @Autowired
    PersonaNaturalPageRepository personaNaturalPageRepository;
    
    @Autowired
    private PersonaJuridicaRepository personaJuridicaRepository;

    @Autowired
    PersonaJuridicaPageRepository personaJuridicaPageRepository;
    
    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    PaisPageRepository paisPageRepository;
    
    @Autowired
    private EstatusRepository estatusRepository;

    @Autowired
    EstatusPageRepository estatusPageRepository;
    
    @Autowired
    private DatosContactoRepository datosContactoRepository;

    @Autowired
    DatosContactoPageRepository datosContactoPageRepository;
    
    @Autowired
    private ContribuyenteRepository contribuyenteRepository;

    @Autowired
    ContribuyentePageRepository contribuyentePageRepository;
    
    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    CitaPageRepository citaPageRepository;
	
    // Metodos para una solicitud
    
    public SolicitudUip obtenerSolicitud(Integer id_solicitud) {
        Optional<SolicitudUip> solicitud = solicitudRepository.findById(id_solicitud);
        logger.debug("Obteniendo datos de la solicitud");
        return solicitud.orElse(new SolicitudUip());
    }
        
    public List<SolicitudUip> obtenerSolicitudes() {
            List<SolicitudUip> solicitud = solicitudRepository.findAll();
            logger.debug("Obteniendo datos de todos las solicitudes");
            return solicitud;
    }


    public Page<SolicitudUip> obtenerSolicitudesByPage(Pageable pageable) {
            logger.debug("Obteniendo datos de todos los usuarios por pagina");
            return solicitudPageRepository.findAll(pageable);
    }


    public SolicitudUip crearSolicitud(SolicitudUip solicitud) {
            logger.debug("Creando solicitud");
            return solicitudRepository.save(solicitud);
    }


    public void eliminarSolicitud(Integer id_solicitud) {
            logger.debug("Eliminando solicitud");
            solicitudRepository.deleteById(id_solicitud);
    }
    
    /*public SolicitudUip retrieveFallback2(Integer min, Integer max) {
        SolicitudUip solicitud = new SolicitudUip();
        solicitud.setNumeroSolicitud("0");
        logger.debug("Utilizando hystrix en caso de fallos en consumirOtroMicroservicio");
        return solicitud;
    }*/
    
    // Metodos para resultado de la solicitud
    
    public Resultado obtenerResultado(Integer id_resultado) {
        Optional<Resultado> resultado = resultadoRepository.findById(id_resultado);
        logger.debug("Obteniendo datos del resultado de una solicitud");
        return resultado.orElse(new Resultado());
    }
        
    public List<Resultado> obtenerResultados() {
            List<Resultado> resultados = resultadoRepository.findAll();
            logger.debug("Obteniendo resultados de las solicitudes");
            return resultados;
    }


    public Page<Resultado> obtenerResultadosByPage(Pageable pageable) {
            logger.debug("Obteniendo resultados por pagina");
            return resultadoPageRepository.findAll(pageable);
    }


    public Resultado crearResultado(Resultado resultado) {
            logger.debug("Creando resultado");
            return resultadoRepository.save(resultado);
    }


    public void eliminarResultado(Integer id_resultado) {
            logger.debug("Eliminando solicitud");
            resultadoRepository.deleteById(id_resultado);
    }
    
    public Resultado retrieveFallback3(Integer min, Integer max) {
        Resultado resultado = new Resultado();
        resultado.setIdResultado(0);
       
        logger.debug("Utilizando hystrix en caso de fallos en consumirOtroMicroservicio");
        return resultado;
    }
    
    // Metodos para razon cita de la solicitud
    
    public RazonCita obtenerRazonCita(Integer id_razonCita) {
        Optional<RazonCita> razonCita = razonCitaRepository.findById(id_razonCita);
        logger.debug("Obteniendo datos de la razon cita de una solicitud");
        return razonCita.orElse(new RazonCita());
    }
        
    public List<RazonCita> obtenerRazonesCita() {
            List<RazonCita> razonesCita = razonCitaRepository.findAll();
            logger.debug("Obteniendo las razones de las solicitudes");
            return razonesCita;
    }


    public Page<RazonCita> obtenerRazonesCitaByPage(Pageable pageable) {
            logger.debug("Obteniendo razones por pagina");
            return razonCitaPageRepository.findAll(pageable);
    }


    public RazonCita crearRazonCita(RazonCita razon) {
            logger.debug("Creando razon");
            return razonCitaRepository.save(razon);
    }


    public void eliminarRazonCita(Integer id_razonCita) {
            logger.debug("Eliminando solicitud");
            razonCitaRepository.deleteById(id_razonCita);
    }
    
    public RazonCita retrieveFallback4(Integer min, Integer max) {
        RazonCita razon = new RazonCita();
        razon.setIdRazoncita(0);
       
        logger.debug("Utilizando hystrix en caso de fallos en consumirOtroMicroservicio");
        return razon;
    }
    
    // Metodos para Persona Natural de la solicitud
    
    public PersonaNatural obtenerPersonaNatural(Integer id_personaNatural) {
        Optional<PersonaNatural> personaNatural = personaNaturalRepository.findById(id_personaNatural);
        logger.debug("Obteniendo datos de la persona natural de una solicitud");
        return personaNatural.orElse(new PersonaNatural());
    }
        
    public List<PersonaNatural> obtenerPersonasNaturales() {
            List<PersonaNatural> personasNaturales = personaNaturalRepository.findAll();
            logger.debug("Obteniendo las personas naturales");
            return personasNaturales;
    }


    public Page<PersonaNatural> obtenerPersonasNaturalesByPage(Pageable pageable) {
            logger.debug("Obteniendo personas naturales por pagina");
            return personaNaturalPageRepository.findAll(pageable);
    }


    public PersonaNatural crearPersonaNatural(PersonaNatural personaNatural) {
            logger.debug("Creando persona natural");
            return personaNaturalRepository.save(personaNatural);
    }


    public void eliminarPersonaNatural(Integer id_personaNatural) {
            logger.debug("Eliminando persona natural");
            personaNaturalRepository.deleteById(id_personaNatural);
    }
    
    
    // Metodos para Persona Juridica de la solicitud
    
    public PersonaJuridica obtenerPersonaJuridica(Integer id_personaJuridica) {
        Optional<PersonaJuridica> personaJuridica = personaJuridicaRepository.findById(id_personaJuridica);
        logger.debug("Obteniendo datos de la persona juridica de una solicitud");
        return personaJuridica.orElse(new PersonaJuridica());
    }
        
    public List<PersonaJuridica> obtenerPersonasJuridicas() {
            List<PersonaJuridica> personasJuridicas = personaJuridicaRepository.findAll();
            logger.debug("Obteniendo las personas juridicas");
            return personasJuridicas;
    }


    public Page<PersonaJuridica> obtenerPersonasJuridicasByPage(Pageable pageable) {
            logger.debug("Obteniendo personas juridicas por pagina");
            return personaJuridicaPageRepository.findAll(pageable);
    }


    public PersonaJuridica crearPersonaJuridica(PersonaJuridica personaJuridica) {
            logger.debug("Creando persona jurica");
            return personaJuridicaRepository.save(personaJuridica);
    }


    public void eliminarPersonaJuridica(Integer id_personaJuridica) {
            logger.debug("Eliminando persona natural");
            personaJuridicaRepository.deleteById(id_personaJuridica);
    }
    
    
    // Metodos para Pais
    
    public Pais obtenerPais(Integer id_pais) {
        Optional<Pais> pais = paisRepository.findById(id_pais);
        logger.debug("Obteniendo datos del pais");
        return pais.orElse(new Pais());
    }
        
    public List<Pais> obtenerPaises() {
            List<Pais> paises = paisRepository.findAll();
            logger.debug("Obteniendo los paises");
            return paises;
    }


    public Page<Pais> obtenerPaisByPage(Pageable pageable) {
            logger.debug("Obteniendo paises por pagina");
            return paisPageRepository.findAll(pageable);
    }


    public Pais crearPais(Pais pais) {
            logger.debug("Creando pais");
            return paisRepository.save(pais);
    }


    public void eliminarPais(Integer id_pais) {
            logger.debug("Eliminando pais");
            personaJuridicaRepository.deleteById(id_pais);
    }
    
    public Pais retrieveFallback7(Integer min, Integer max) {
        Pais pais = new Pais();
        pais.setIdPais(0);
       
        logger.debug("Utilizando hystrix en caso de fallos en consumirOtroMicroservicio");
        return pais;
    }
    
     // Metodos para Estatus
    
    public Estatus obtenerEstatus(Integer id_estatus) {
        Optional<Estatus> estatus = estatusRepository.findById(id_estatus);
        logger.debug("Obteniendo datos del estatus");
        return estatus.orElse(new Estatus());
    }
        
    public List<Estatus> obtenerEstatus() {
            List<Estatus> estatus = estatusRepository.findAll();
            logger.debug("Obteniendo los estatus");
            return estatus;
    }


    public Page<Estatus> obtenerEstatusByPage(Pageable pageable) {
            logger.debug("Obteniendo estatus por pagina");
            return estatusPageRepository.findAll(pageable);
    }


    public Estatus crearEstatus(Estatus estatus) {
            logger.debug("Creando estatus");
            return estatusRepository.save(estatus);
    }


    public void eliminarEstatus(Integer id_estatus) {
            logger.debug("Eliminando estatus");
            estatusRepository.deleteById(id_estatus);
    }
    
    public Estatus retrieveFallback8(Integer min, Integer max) {
        Estatus estatus = new Estatus();
        estatus.setIdestatus(0);
       
        logger.debug("Utilizando hystrix en caso de fallos en consumirOtroMicroservicio");
        return estatus;
    }
    
    // Metodos para Datos contacto
    
    public DatosContacto obtenerDatosContacto(Integer id_datosContacto) {
        Optional<DatosContacto> datosContacto = datosContactoRepository.findById(id_datosContacto);
        logger.debug("Obteniendo datos del contacto");
        return datosContacto.orElse(new DatosContacto());
    }
        
    public List<DatosContacto> obtenerDatosContacto() {
            List<DatosContacto> datosContacto = datosContactoRepository.findAll();
            logger.debug("Obteniendo datos contacto");
            return datosContacto;
    }


    public Page<DatosContacto> obtenerDatosContactoByPage(Pageable pageable) {
            logger.debug("Obteniendo datos contacto por pagina");
            return datosContactoPageRepository.findAll(pageable);
    }


    public DatosContacto crearDatosContacto(DatosContacto datosContacto) {
            logger.debug("Creando datos contacto");
            return datosContactoRepository.save(datosContacto);
    }


    public void eliminarDatosContacto(Integer id_datosContacto) {
            logger.debug("Eliminando datos");
            datosContactoRepository.deleteById(id_datosContacto);
    }
    
    public DatosContacto retrieveFallback9(Integer min, Integer max) {
        DatosContacto datosContacto = new DatosContacto();
        datosContacto.setId(null);
       
        logger.debug("Utilizando hystrix en caso de fallos en consumirOtroMicroservicio");
        return datosContacto;
    }
    
    // Metodos para Contribuyente
    
    public Contribuyente obtenerContribuyente(Integer id_contribuyente) {
        Optional<Contribuyente> contribuyente = contribuyenteRepository.findById(id_contribuyente);
        logger.debug("Obteniendo datos del Contribuyente");
        return contribuyente.orElse(new Contribuyente());
    }
        
    public List<Contribuyente> obtenerContribuyente() {
            List<Contribuyente> contribuyente = contribuyenteRepository.findAll();
            logger.debug("Obteniendo contribuyentes");
            return contribuyente;
    }


    public Page<Contribuyente> obtenerContribuyenteByPage(Pageable pageable) {
            logger.debug("Obteniendo contribuyentes por pagina");
            return contribuyentePageRepository.findAll(pageable);
    }


    public Contribuyente crearContribuyente(Contribuyente contribuyente) {
            logger.debug("Creando datos contacto");
            return contribuyenteRepository.save(contribuyente);
    }


    public void eliminarContribuyente(Integer id_contribuyente) {
            logger.debug("Eliminando datos");
            contribuyenteRepository.deleteById(id_contribuyente);
    }
    
    // Metodos para Contribuyente
    
    public Cita obtenerCita(Integer id_cita) {
        Optional<Cita> cita = citaRepository.findById(id_cita);
        logger.debug("Obteniendo cita");
        return cita.orElse(new Cita());
    }
        
    public List<Cita> obtenerCitas() {
            List<Cita> citas = citaRepository.findAll();
            logger.debug("Obteniendo citas");
            return citas;
    }


    public Page<Cita> obtenerCitasByPage(Pageable pageable) {
            logger.debug("Obteniendo citas por pagina");
            return citaPageRepository.findAll(pageable);
    }


    public Cita crearCita(Cita cita) {
            logger.debug("Creando cita");
            return citaRepository.save(cita);
    }


    public void eliminarCita(Integer id_cita) {
            logger.debug("Eliminando cita");
            citaRepository.deleteById(id_cita);
    }
    
    public Cita retrieveFallback11(Integer min, Integer max) {
        Cita cita = new Cita();
        cita.setIdCita(0);
       
        logger.debug("Utilizando hystrix en caso de fallos en consumirOtroMicroservicio");
        return cita;
    }
}
