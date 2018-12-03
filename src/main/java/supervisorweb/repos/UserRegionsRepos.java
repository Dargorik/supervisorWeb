package supervisorweb.repos;

import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.Region;
import supervisorweb.domain.User;
import supervisorweb.domain.UserRegions;

public interface UserRegionsRepos extends CrudRepository<UserRegions, Long> {
    UserRegions findAllByUserAndRegion(User user, Region region);
}
