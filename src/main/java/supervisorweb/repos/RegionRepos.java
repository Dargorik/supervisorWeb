package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import supervisorweb.domain.Region;

import java.util.List;

public interface RegionRepos extends JpaRepository<Region,Integer> {
    Region findByName(String name);

    @Override
    @Query(value="select r from Region r ORDER BY name")
    List<Region> findAll();
}
