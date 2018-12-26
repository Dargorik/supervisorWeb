package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.Position;
import supervisorweb.domain.PositionDuties;
import supervisorweb.domain.TypeOfWork;

import java.util.List;

public interface PositionDutiesRepos extends JpaRepository<PositionDuties, Integer> {
    PositionDuties findByPositionAndTypeOfWork(Position position, TypeOfWork typeOfWork);

    @Query(value="select pd from PositionDuties pd where pd.typeOfWork=:typeOfWork and pd.position=:position and not pd.idPositionDuties=:id")
    PositionDuties findByTypeOfWorkAndPositionAndNotId(@Param("typeOfWork") TypeOfWork typeOfWork,@ Param("position") Position position, @Param("id") Integer id);

    List<PositionDuties> findByPosition(Position position);

    @Override
    @Query(value="select pd from PositionDuties pd order by pd.position.name , pd.typeOfWork.name")
    List<PositionDuties> findAll();
}
