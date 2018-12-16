package supervisorweb.service;

import supervisorweb.domain.TypeOfWorkPerformed;
import supervisorweb.domain.User;

import java.util.List;

public interface TypeOfWorkPerformedService {
    TypeOfWorkPerformed findByName(String name);

    List<TypeOfWorkPerformed> findAll();

    String update(Integer updId, String updName);

    String delete(Integer delId);

    String add(String name);

    TypeOfWorkPerformed findById(Integer id);

    List<TypeOfWorkPerformed> findForUser(User user);
}
