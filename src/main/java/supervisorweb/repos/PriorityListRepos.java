package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import supervisorweb.domain.PriorityList;

public interface PriorityListRepos extends JpaRepository<PriorityList, Integer> {

    PriorityList findByName(String name);
}
