package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.Address;
import supervisorweb.domain.CompletedWork;
import supervisorweb.domain.TypeOfWork;

import java.sql.Timestamp;

public interface CompletedWorkRepos extends JpaRepository<CompletedWork,Integer> {

    @Query(value = "select MAX(cw.timestamp_send) from CompletedWork cw where cw.address=:address and  cw.typeOfWorkPerformed in (select l.typeOfWorkPerformed from ListTypesInPerfomedWork l where l.typeOfWork=:typeOfWork) group by cw.address")
    Timestamp findByAddressAndTypeOfWork(@Param("address") Address address, @Param("typeOfWork") TypeOfWork typeOfWork);
}
