package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.ListTypesInPerfomedWork;
import supervisorweb.domain.TypeOfWork;
import supervisorweb.domain.TypeOfWorkPerformed;

import java.util.List;

public interface ListTypesInPerfomedWorkRepos extends JpaRepository<ListTypesInPerfomedWork, Integer> {
    List<ListTypesInPerfomedWork> findByTypeOfWorkPerformed(TypeOfWorkPerformed typeOfWorkPerformed);

    @Query(value="select ltInPw from ListTypesInPerfomedWork ltInPw where ltInPw.typeOfWorkPerformed=:typeOfWorkPerformed and ltInPw.typeOfWork=:typeOfWork and not ltInPw.idListTypesInPerfomedWork=:id")
    ListTypesInPerfomedWork findByTypeOfWorkPerformedAndTypeOfWorkAndNotId(@Param("typeOfWorkPerformed") TypeOfWorkPerformed typeOfWorkPerformed, @Param("typeOfWork") TypeOfWork typeOfWork, @Param("id") Integer id);

    ListTypesInPerfomedWork findAllByTypeOfWorkPerformedAndTypeOfWork(TypeOfWorkPerformed typeOfWorkPerformed, TypeOfWork typeOfWork);

    @Override
    @Query(value="select ltInPw from ListTypesInPerfomedWork ltInPw order by ltInPw.typeOfWorkPerformed.name , ltInPw.typeOfWork.name")
    List<ListTypesInPerfomedWork> findAll();
}
