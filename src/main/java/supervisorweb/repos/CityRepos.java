package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import supervisorweb.domain.City;

import java.util.List;

public interface CityRepos extends JpaRepository<City, Integer> {
    City findByName(String name);

    @Override
    @Query(value="select c from City c ORDER BY name")
    List<City> findAll();
}
