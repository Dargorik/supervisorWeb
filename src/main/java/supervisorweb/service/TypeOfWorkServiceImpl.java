package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.City;
import supervisorweb.domain.TypeOfWork;
import supervisorweb.repos.TypeOfWorkRepos;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeOfWorkServiceImpl implements  TypeOfWorkService {
    @Autowired
    TypeOfWorkRepos typeOfWorkRepos;

    @Override
    public TypeOfWork findByName(String name) {
        return typeOfWorkRepos.findByName(name);
    }

    @Override
    public List<TypeOfWork> findAll() {
        return typeOfWorkRepos.findAll().stream().sorted((x, y) -> x.getName().compareTo(y.getName())).collect(Collectors.toList());
    }

    @Override
    public String update(Integer updIdTypeOfWorkRepos, String updName) {
        if (updName == null || updName.isEmpty())
            return "Поле с именем заполненно не корректно!";
        TypeOfWork typeOfWork = typeOfWorkRepos.findById(updIdTypeOfWorkRepos).orElse(null);
        if (typeOfWork != null) {
            typeOfWork.setName(updName);
            typeOfWorkRepos.save(typeOfWork);
            return "Изменение прошло успешно!";
        } else return "Изменение не произошло!";
    }

    @Override
    public String delete(Integer delIdCity) {
        TypeOfWork typeOfWork = typeOfWorkRepos.findById(delIdCity).orElse(null);
        if (typeOfWork == null) {
            return "Данный тип работы не найден!";
        } else {
            typeOfWorkRepos.delete(typeOfWork);
            return "тип работы успешно удалён!";
        }
    }

    @Override
    public String add(String name) {
        if (name != null && !name.isEmpty()) {
            TypeOfWork typeOfWork = new TypeOfWork(name);
            TypeOfWork c = typeOfWorkRepos.findByName(typeOfWork.getName());
            if (c == null) {
                typeOfWorkRepos.save(typeOfWork);
            } else return "Данный тип работы уже есть в базе данных!";
        } else return "Поле с именем заполненно не корректно!";
        return "Новый тип работы успешно добавлен!";
    }

    @Override
    public TypeOfWork findById(Integer id) {
        return typeOfWorkRepos.findById(id).orElse(null);
    }
}
