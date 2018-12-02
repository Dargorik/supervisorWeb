package supervisorweb.repos;

import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.Street;

public interface StreetRepos extends CrudRepository<Street,Long> {

    Street findByName(String street);
}
