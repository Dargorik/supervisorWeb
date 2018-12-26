package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.City;
import supervisorweb.domain.Region;
import supervisorweb.domain.User;
import supervisorweb.domain.UserRegions;

import java.util.List;

public interface UserRegionsRepos extends JpaRepository<UserRegions, Integer> {
    UserRegions findAllByUserAndRegion(User user, Region region);

    @Query(value="select ur from UserRegions ur where ur.user=:user and ur.region=:region and not ur.idUserRegions=:id")
    UserRegions findByUserAndRegionAndNotId(@Param("user") User user, @ Param("region") Region region, @Param("id") Integer id);

    List<UserRegions> findByUser(User user);

    @Override
    @Query(value="select ur from UserRegions ur order by ur.user.firstName,ur.user.lastName, ur.region.name")
    List<UserRegions> findAll();
}
