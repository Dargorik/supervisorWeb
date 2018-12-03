package supervisorweb.repos;

import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.TypeOfWork;

public interface TypeOfWorkRepos extends CrudRepository<TypeOfWork, Long>{
    TypeOfWork findByName(String name);
}
