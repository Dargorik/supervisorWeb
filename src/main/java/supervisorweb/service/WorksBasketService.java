package supervisorweb.service;

import supervisorweb.domain.Address;
import supervisorweb.domain.User;
import supervisorweb.domain.WorksBasket;

import java.util.List;

public interface WorksBasketService {
    List<WorksBasket> findAll();

    List<WorksBasket> findById(Integer id);

    List<Address> findOtherAddresses(User user);

    String add(Integer idUsers, Integer idAddress, String numberCompletedEntrances, Integer idTypeOfWorkPerformed, String comment);

    List<WorksBasket> findByUser(User user);

    String delete(Integer delId);

    List<Address> findRelevanceOtherAddresses(User user, Integer idTypeOfWorkPerformed);

    Integer findCount(User user);
}
