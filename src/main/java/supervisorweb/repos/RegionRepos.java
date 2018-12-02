package supervisorweb.repos;

import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.Region;

public interface RegionRepos extends CrudRepository<Region,Long> {

    Region findByName(String name);
}
