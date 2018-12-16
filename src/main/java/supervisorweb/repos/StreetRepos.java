package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import supervisorweb.domain.Street;

import java.util.List;

public interface StreetRepos extends JpaRepository<Street, Integer> {
    Street findByName(String name);

    @Override
    @Query(value="select s from Street s ORDER BY name")
    List<Street> findAll();
}
