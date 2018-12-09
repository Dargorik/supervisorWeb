package supervisorweb.service;

import supervisorweb.domain.City;
import supervisorweb.domain.TypeOfWork;

import java.util.List;

public interface TypeOfWorkService {
    TypeOfWork findByName(String name);

    List<TypeOfWork> findAll();


    String update(Integer updIdTypeOfWorkRepos, String updName);

    String delete(Integer delIdTypeOfWorkRepos);

    String add(String name);

    TypeOfWork findById(Integer updId);
}
