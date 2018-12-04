package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import supervisorweb.domain.City;

public interface CityRepos extends JpaRepository<City, Integer> {
    City findByName(String name);
}
