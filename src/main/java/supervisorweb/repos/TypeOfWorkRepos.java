package supervisorweb.repos;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.TypeOfWork;
import supervisorweb.domain.TypeOfWorkPerformed;

import java.util.List;

public interface TypeOfWorkRepos extends JpaRepository<TypeOfWork, Integer>{
    TypeOfWork findByName(String name);

    @Override
    @Query(value="select tOfW from TypeOfWork tOfW ORDER BY name")
    List<TypeOfWork> findAll();

    @Query(value="select list.typeOfWork from ListTypesInPerfomedWork list where list.typeOfWorkPerformed=:typeOfWorkPerformed")
    List<TypeOfWork> findTypeOfWorkByTypeOfWorkPerformed(@Param("typeOfWorkPerformed") TypeOfWorkPerformed typeOfWorkPerformed);
}
