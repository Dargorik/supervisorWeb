package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import supervisorweb.domain.PriorityList;

import java.util.List;

public interface PriorityListRepos extends JpaRepository<PriorityList, Integer> {
    PriorityList findByName(String name);

    @Override
    @Query(value="select pl from PriorityList pl ORDER BY name")
    List<PriorityList> findAll();
}
