package gt.gob.sat.uip.service_solicituduip.repositories;

import org.springframework.data.repository.CrudRepository;
import gt.gob.sat.uip.service_solicituduip.model.User;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Override
	List<User> findAll();
}