package supervisorweb.repos;

import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.City;

public interface CityRepos extends CrudRepository<City, Long> {
    City findByName(String city);
}
