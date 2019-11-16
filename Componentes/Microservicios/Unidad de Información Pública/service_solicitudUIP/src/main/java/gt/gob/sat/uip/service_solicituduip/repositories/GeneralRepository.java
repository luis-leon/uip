package gt.gob.sat.uip.service_solicituduip.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import gt.gob.sat.uip.service_solicituduip.model.User;
import java.math.BigInteger;

@Repository
public class GeneralRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<User> obtenerDatosUsuario(String pName) {
		String query = "SELECT email, "
				     + "name, " 
				     + "id "
				     + "FROM test.user "
				     + "WHERE name = :name";
		
		Query q = entityManager.createNativeQuery(query, User.class);
		q.setParameter("name", pName);
		return q.getResultList();
	}
        
        public void actualizarSolicitud(int idSolicitud, int idEstatus) {
            
            String query = "update sat_uip.solicitud_uip set id_estatus = :idEstatus where id_solicitud = :idSolicitud";
            Query q = entityManager.createNativeQuery(query);
            q.setParameter("idSolicitud", idSolicitud);
            q.setParameter("idEstatus", idEstatus);
        }
        
        public List<BigInteger> obtenerSecPersonaNatural() {
            String query = "select nextval('sat_uip.sec_persona_natural');";
            Query q = entityManager.createNativeQuery(query);
            return q.getResultList();
        }
        
        public List<BigInteger> obtenerSecPersonaJuridica() {
            String query = "select nextval('sat_uip.sec_persona_juridica');";
            Query q = entityManager.createNativeQuery(query);
            return q.getResultList();
        }
	
        public List<BigInteger> obtenerSecContri() {
            String query = "select nextval('sat_uip.sec_contri');";
            Query q = entityManager.createNativeQuery(query);
            return q.getResultList();
        }
        
        public List<BigInteger> obtenerSecSolicitud() {
            String query = "select nextval('sat_uip.sec_solicitud');";
            Query q = entityManager.createNativeQuery(query);
            return q.getResultList();
        }
	
}