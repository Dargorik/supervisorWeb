package supervisorweb.repos;

        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import supervisorweb.domain.Address;
        import supervisorweb.domain.LastCompletedDateAddress;
        import supervisorweb.domain.TypeOfWork;

        import java.sql.Timestamp;
        import java.util.List;


public interface LastCompletedDateAddressRepos extends JpaRepository<LastCompletedDateAddress, Integer> {

    LastCompletedDateAddress findByAddressAndTypeOfWork(Address address, TypeOfWork typeOfWork);

    @Query(value = "select t from LastCompletedDateAddress t " +
            "where t.address.city.name like :city " +
            "and t.address.street.name like :street " +
            "and t.address.region.name like :region " +
            "and t.address.priorityList.name like :priority " +
            "GROUP BY t.address  order by t.address.city.name,t.address.street.name,t.address.houseNumber",
            countQuery = "select count(t) from LastCompletedDateAddress t " +
                    "where t.address.city.name like :city " +
                    "and t.address.street.name like :street " +
                    "and t.address.region.name like :region " +
                    "and t.address.priorityList.name like :priority " +
                    "GROUP BY t.address  order by t.address.city.name,t.address.street.name,t.address.houseNumber")
    Page<LastCompletedDateAddress> findAllAddress(@Param("city")String city, @Param("street")String street, @Param("region")String region, @Param("priority")String priority, Pageable pageable);

    @Query(value = "select t from LastCompletedDateAddress t " +
            "where t.typeOfWork=:typeOfWork " +
            "and t.address.city.name like :city " +
            "and t.address.street.name like :street " +
            "and t.address.region.name like :region " +
            "and t.address.priorityList.name like :priority " +
            "order by t.address.city.name,t.address.street.name,t.address.houseNumber",
            countQuery = "select count(t) from LastCompletedDateAddress t " +
                    "where t.typeOfWork=:typeOfWork " +
                    "and t.address.city.name like :city " +
                    "and t.address.street.name like :street " +
                    "and t.address.region.name like :region " +
                    "and t.address.priorityList.name like :priority " +
                    "order by t.address.city.name,t.address.street.name,t.address.houseNumber")
    Page<LastCompletedDateAddress> findAllAddressByTypeWork(@Param("typeOfWork") TypeOfWork typeOfWork, @Param("city")String city, @Param("street")String street, @Param("region")String region, @Param("priority")String priority, Pageable pageable);

    @Query(value = "select t from LastCompletedDateAddress t where t.typeOfWork=:typeOfWork  order by t.address.city.name,t.address.street.name,t.address.houseNumber")
    List<LastCompletedDateAddress> findAllAddressByTypeWork2(@Param("typeOfWork") TypeOfWork typeOfWork);

    @Query(value = "select t from LastCompletedDateAddress t where t.id in (:id)  order by t.address.city.name,t.address.street.name,t.address.houseNumber",
            countQuery = "select count(t) from LastCompletedDateAddress t where t.id in (:id)  order by t.address.city.name,t.address.street.name,t.address.houseNumber")
    Page<LastCompletedDateAddress> findAllById(@Param("id") List<Integer> id, Pageable pageable);

}
