/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.uip.service_asignacionesrevisores.repositories;

import gt.gob.sat.uip.service_asignacionesrevisores.model.CatRevisores;
import gt.gob.sat.uip.service_asignacionesrevisores.model.ColaboradorRevisor;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author alfdeleon
 */
public interface ColaboradorRevisorRepository extends CrudRepository<ColaboradorRevisor, Integer>{
      @Override
	List<ColaboradorRevisor> findAll();
}
