package supervisorweb.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.Address;
import supervisorweb.domain.CompletedWork;
import supervisorweb.domain.TypeOfWork;

import java.sql.Timestamp;
import java.util.List;

public interface CompletedWorkRepos extends JpaRepository<CompletedWork, Integer> {

    @Query(value = "select MAX(cw.timestamp_send) from CompletedWork cw where cw.address=:address and  cw.typeOfWorkPerformed in (select l.typeOfWorkPerformed from ListTypesInPerfomedWork l where l.typeOfWork=:typeOfWork) group by cw.address")
    Timestamp findByAddressAndTypeOfWork(@Param("address") Address address, @Param("typeOfWork") TypeOfWork typeOfWork);

    @Query(value = "select cw from CompletedWork cw " +
            "where cw.address.city.name like :city " +
            "and cw.user.firstName like :firstName " +
            "and cw.user.lastName like :lastName " +
            "and cw.address.street.name like :street " +
            "and cw.address.region.name like :region " +
            "and cw.typeOfWorkPerformed.name like :typeWork",
            countQuery = "select count(cw) from CompletedWork cw " +
                    "where cw.address.city.name like :city " +
                    "and cw.user.firstName like :firstName " +
                    "and cw.user.lastName like :lastName " +
                    "and cw.address.street.name like :street " +
                    "and cw.address.region.name like :region " +
                    "and cw.typeOfWorkPerformed.name like :typeWork")
    List<CompletedWork> findAllByFilters(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("city") String city, @Param("street") String street, @Param("region") String region, @Param("typeWork") String typeWork);


    @Query(value = "select cw from CompletedWork cw " +
            "where cw.idCompletedWork in (:id)",
            countQuery = "select count(cw) from CompletedWork cw " +
                    "where cw.idCompletedWork in (:id)")
    Page<CompletedWork> findAllByPage(@Param("id") List<Integer> id, Pageable pageable);
}
