package gt.gob.sat.uip.service_solicituduip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import gt.gob.sat.uip.service_solicituduip.model.User;

//public interface UserPageRepository extends PagingAndSortingRepository<User, Long> {
public interface UserPageRepository extends JpaRepository<User, Long> {

}