package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import supervisorweb.domain.Street;

public interface StreetRepos extends JpaRepository<Street, Integer> {
    Street findByName(String name);
}
