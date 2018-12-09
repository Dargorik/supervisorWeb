package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import supervisorweb.domain.Region;

public interface RegionRepos extends JpaRepository<Region,Integer> {

    Region findByName(String name);
}
