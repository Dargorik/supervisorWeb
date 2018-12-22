package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.User;
import supervisorweb.domain.WorksBasket;

import java.util.List;

public interface WorksBasketRepos extends JpaRepository<WorksBasket,Integer>{


    @Query(value="select wb from WorksBasket wb where wb.user=:user ORDER BY wb.address.city.name,wb.address.street.name,wb.address.houseNumber")
    List<WorksBasket> findByUser(@Param("user") User user);

    @Override
    List<WorksBasket> findAll();
}
