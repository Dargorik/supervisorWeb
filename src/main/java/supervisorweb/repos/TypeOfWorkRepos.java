package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.Position;
import supervisorweb.domain.TypeOfWork;
import supervisorweb.domain.TypeOfWorkPerformed;

import java.util.List;

public interface TypeOfWorkRepos extends JpaRepository<TypeOfWork, Integer>{
    TypeOfWork findByName(String name);

    @Query(value="select tOfW from TypeOfWork tOfW where tOfW.name=:name and not tOfW.idTypeOfWork=:id")
    TypeOfWork findByNameAndNotId(@Param("name") String name, @Param("id") Integer id);

    @Override
    @Query(value="select tOfW from TypeOfWork tOfW ORDER BY name")
    List<TypeOfWork> findAll();

    @Query(value="select list.typeOfWork from ListTypesInPerfomedWork list where list.typeOfWorkPerformed=:typeOfWorkPerformed")
    List<TypeOfWork> findTypeOfWorkByTypeOfWorkPerformed(@Param("typeOfWorkPerformed") TypeOfWorkPerformed typeOfWorkPerformed);

    @Query(value="select pd.typeOfWork from PositionDuties pd where  pd.position=:position")
    List<TypeOfWork> findByUsersPosition(@Param("position")Position position);
}
