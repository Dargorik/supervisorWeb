package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.Region;

import java.util.List;

public interface RegionRepos extends JpaRepository<Region,Integer> {
    Region findByName(String name);

    @Query(value="select r from Region r where r.name=:name and not r.idRegion=:id")
    Region findByNameAndNotId(@Param("name") String name, @Param("id") Integer id);

    @Override
    @Query(value="select r from Region r ORDER BY name")
    List<Region> findAll();
}
