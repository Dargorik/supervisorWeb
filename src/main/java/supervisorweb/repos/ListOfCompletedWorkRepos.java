package supervisorweb.repos;

import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.ListOfCompletedWork;

public interface ListOfCompletedWorkRepos extends CrudRepository<ListOfCompletedWork,Long> {
    Iterable<ListOfCompletedWork> findByComments(String filter);
}
