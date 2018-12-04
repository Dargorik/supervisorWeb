package supervisorweb.repos;

import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.CompletedWork;

public interface CompletedWorkRepos extends CrudRepository<CompletedWork,Long> {
}
