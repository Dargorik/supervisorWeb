package supervisorweb.service;

import supervisorweb.domain.TypeOfWorkPerformed;

import java.util.List;

public interface TypeOfWorkPerformedService {
    TypeOfWorkPerformed findByName(String name);

    List<TypeOfWorkPerformed> findAll();


    String update(Integer updIdTypeOfWorkPerformed, String updName);

    String delete(Integer delIdTypeOfWorkPerformed);

    String add(String name);

    TypeOfWorkPerformed findById(Integer id);
}
