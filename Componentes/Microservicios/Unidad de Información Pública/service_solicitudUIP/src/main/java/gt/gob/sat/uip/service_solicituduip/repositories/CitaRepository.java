/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.uip.service_solicituduip.repositories;
import org.springframework.data.repository.CrudRepository;
import gt.gob.sat.uip.service_solicituduip.model.Cita;
import java.util.List;
/**
 *
 * @author ajacalito
 */

public interface CitaRepository extends CrudRepository<Cita, Integer> {

	@Override
	List<Cita> findAll();
}