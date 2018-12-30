package supervisorweb.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.*;

import java.util.List;

public interface AddressRepos extends JpaRepository<Address, Integer> {

    Address findByCityAndStreetAndHouseNumberLike(City city, Street street, String houseNumber);

    Address findByIdAddress(Integer idAddress);

    @Query(value="select a from Address a where a.city=:city and a.street=:street and  a.houseNumber=:houseNumber and not a.idAddress=:id")
    Address findByCityAndStreetAndHouseNumberAndNotId(@Param("city") City city, @Param("street") Street street, @Param("houseNumber") String houseNumber, @Param("id") Integer id);

    @Override
    @Query(value = "select a from Address a ORDER BY a.city.name,a.street.name,a.houseNumber")
    List<Address> findAll();

    @Query(value = "select a from Address a " +
            "where a.city.name like :city " +
            "and a.street.name like :street " +
            "and a.region.name like :region " +
            "and a.priorityList.name like :priority " +
            "ORDER BY a.city.name,a.street.name,a.houseNumber",
            countQuery = "select count(a) from Address a " +
                    "where a.city.name like :city " +
                    "and a.street.name like :street " +
                    "and a.region.name like :region " +
                    "and a.priorityList.name like :priority " +
                    "order by a.city.name,a.street.name,a.houseNumber")
    Page<Address> findAllByPage(@Param("city")String city, @Param("street")String street, @Param("region")String region, @Param("priority")String priority, Pageable pageable);

    @Query(value = "select a from Address a WHERE a.region in (select region from UserRegions where user=:user) AND NOT a.idAddress in (select wb.address from WorksBasket wb where wb.user=:user) order by a.city.name,a.street.name,a.houseNumber")
    List<Address> findByUserRegions(@Param("user") User user);

    List<Address> findByRegion(Region region);

    List<Address> findByPriorityList(PriorityList priorityList);
}
