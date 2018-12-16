package supervisorweb.service;

import supervisorweb.domain.TypeOfWork;

import java.util.List;

public interface TypeOfWorkService {
    TypeOfWork findByName(String name);

    List<TypeOfWork> findAll();

    String update(Integer updId, String updName);

    String delete(Integer delId);

    String add(String name);

    TypeOfWork findById(Integer updId);
}
