package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.PriorityList;
import supervisorweb.domain.Street;

import java.util.List;

public interface PriorityListRepos extends JpaRepository<PriorityList, Integer> {
    PriorityList findByName(String name);

    @Query(value="select pl from PriorityList pl where pl.name=:name and not pl.idPriorityList=:id")
    PriorityList findByNameAndNotId(@Param("name") String name, @Param("id") Integer id);

    @Override
    @Query(value="select pl from PriorityList pl ORDER BY name")
    List<PriorityList> findAll();
}
