/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.sat.uip.service_solicituduip.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import gt.gob.sat.uip.service_solicituduip.model.Asignacion;
/**
 *
 * @author ajacalito
 */

public interface AsignacionPageRepository extends JpaRepository<Asignacion, Long> {

}