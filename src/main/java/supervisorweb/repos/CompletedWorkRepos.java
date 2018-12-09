package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import supervisorweb.domain.CompletedWork;

public interface CompletedWorkRepos extends JpaRepository<CompletedWork,Integer> {
}
