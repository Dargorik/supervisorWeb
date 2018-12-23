package supervisorweb.repos;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import supervisorweb.domain.Address;
        import supervisorweb.domain.LastCompletedDateAddress;
        import supervisorweb.domain.TypeOfWork;

        import java.util.List;

public interface LastCompletedDateAddressRepos extends JpaRepository<LastCompletedDateAddress, Integer> {
    LastCompletedDateAddress findByAddressAndTypeOfWork(Address address, TypeOfWork typeOfWork);

    @Query(value = "select t from LastCompletedDateAddress t GROUP BY t.address  order by t.address.city.name,t.address.street.name,t.address.houseNumber")
    List<LastCompletedDateAddress> findAllAddress();

    @Query(value = "select t from LastCompletedDateAddress t where t.typeOfWork=:typeOfWork  order by t.address.city.name,t.address.street.name,t.address.houseNumber")
    List<LastCompletedDateAddress> findAllAddressByTypeWork(@Param("typeOfWork") TypeOfWork typeOfWork);
}
