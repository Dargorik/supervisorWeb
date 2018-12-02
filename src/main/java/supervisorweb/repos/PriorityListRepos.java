package supervisorweb.repos;

import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.PriorityList;

public interface PriorityListRepos extends CrudRepository<PriorityList,Long> {

    PriorityList findByName(String name);
}
