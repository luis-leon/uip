package gt.gob.sat.expel.microservicio_node_alfresco.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gt.gob.sat.expel.microservicio_node_alfresco.dto.NodeDto;
import gt.gob.sat.expel.microservicio_node_alfresco.services.ClaseService;
@Repository
public class GeneralRepository {

	@PersistenceContext
	private EntityManager entityManager;
	final static Logger LOG = LoggerFactory.getLogger(ClaseService.class);
        

        public List<NodeDto> getNodeRefDocto(String pExpediente, String pDocto){
            String query = "select an.uuid from alf_node an "
                    + "inner join alf_store ast on an.store_id = ast.id "
                    + "inner join alf_node_properties anp on anp.node_id = an.id "
                    + "inner join alf_child_assoc aca on aca.child_node_id = an.id "
                    + "where anp.string_value = :pDocto and "
                    + "aca.parent_node_id = (select an.id from alf_node an "
                    + "inner join alf_store ast on an.store_id = ast.id "
                    + "inner join alf_node_properties anp on anp.node_id = an.id "
                    + "where anp.string_value = :pExpediente limit 1)";
            Query q = entityManager.createNativeQuery(query, NodeDto.class);
            q.setParameter("pExpediente", pExpediente);
            q.setParameter("pDocto", pDocto);
            LOG.debug("queryResult" + q.getResultList());
            return q.getResultList();
        }

}