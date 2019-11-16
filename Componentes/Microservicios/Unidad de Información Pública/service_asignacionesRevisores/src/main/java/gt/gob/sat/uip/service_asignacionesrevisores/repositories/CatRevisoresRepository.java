/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.uip.service_asignacionesrevisores.repositories;

import gt.gob.sat.uip.service_asignacionesrevisores.model.CatRevisores;
import gt.gob.sat.uip.service_asignacionesrevisores.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author alfdeleon
 */
public interface CatRevisoresRepository extends CrudRepository<CatRevisores, Integer> {
    @Override
	List<CatRevisores> findAll();
}
