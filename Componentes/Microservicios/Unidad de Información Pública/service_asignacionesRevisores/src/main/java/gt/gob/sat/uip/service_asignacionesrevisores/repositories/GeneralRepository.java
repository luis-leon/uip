package gt.gob.sat.uip.service_asignacionesrevisores.repositories;

import gt.gob.sat.uip.service_asignacionesrevisores.model.CatRevisores;
import gt.gob.sat.uip.service_asignacionesrevisores.model.ColaboradorRevisor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import gt.gob.sat.uip.service_asignacionesrevisores.model.User;


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
        
      /*  public List<colaboradorRevisorDto> obtenerColaboradores(){
        String query ="select *"
                + " from sat_uip.colaborador_revisor"; 
        Query q = entityManager.createNativeQuery(query, colaboradorRevisorDto.class);
        return q.getResultList();
        }
        *//*
        public List<CatRevisores> obtenerCatRevisores(){
        String query ="select id_cat,descripcion_cargo, ultimo_asignado from sat_uip.cat_revisores"; 
        Query q = entityManager.createNativeQuery(query, CatRevisores.class);
        return q.getResultList();
        }*/
        
        public List<ColaboradorRevisor> obtenerColaboradoresCategoria(int pCategoria){
        String query ="select asig.id_ord_revisor as \"id_revisor\",cr.nombre, cr.correo from sat_uip.colaborador_revisor cr inner join sat_uip.asignacion asig on cr.id_revisor=asig.id_revisor where asig.id_cat=:pCategoria"; 
        Query q = entityManager.createNativeQuery(query, ColaboradorRevisor.class);
        q.setParameter("pCategoria", pCategoria);
        return q.getResultList();
        }
        
        
	
	
}