package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import supervisorweb.domain.Region;
import supervisorweb.domain.User;
import supervisorweb.domain.UserRegions;

public interface UserRegionsRepos extends JpaRepository<UserRegions, Integer> {
    UserRegions findAllByUserAndRegion(User user, Region region);
}
