package supervisorweb.repos;

import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.TypeOfWorkPerformed;

public interface TypeOfWorkPerformedRepos extends CrudRepository<TypeOfWorkPerformed, Long> {
    TypeOfWorkPerformed findByName(String name);

}
