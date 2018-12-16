package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import supervisorweb.domain.City;
import supervisorweb.domain.Region;
import supervisorweb.domain.User;
import supervisorweb.domain.UserRegions;

import java.util.List;

public interface UserRegionsRepos extends JpaRepository<UserRegions, Integer> {
    UserRegions findAllByUserAndRegion(User user, Region region);

    List<UserRegions> findByUser(User user);

}
