package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.TypeOfWork;
import supervisorweb.domain.TypeOfWorkPerformed;
import supervisorweb.repos.TypeOfWorkPerformedRepos;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeOfWorkPerformedServiceImpl implements TypeOfWorkPerformedService {
    @Autowired
    private TypeOfWorkPerformedRepos typeOfWorkPerformedRepos;

    @Override
    public TypeOfWorkPerformed findByName(String name) {
        return typeOfWorkPerformedRepos.findByName(name);
    }

    @Override
    public List<TypeOfWorkPerformed> findAll() {
        return typeOfWorkPerformedRepos.findAll().stream().sorted((x, y) -> x.getName().compareTo(y.getName())).collect(Collectors.toList());
    }

    @Override
    public String update(Integer updIdTypeOfWorkPerformed, String updName) {
        if (updName == null || updName.isEmpty())
            return "Поле с именем заполненно не корректно!";
        TypeOfWorkPerformed typeOfWorkPerformed  = typeOfWorkPerformedRepos.findById(updIdTypeOfWorkPerformed).orElse(null);
        if (typeOfWorkPerformed != null) {
            typeOfWorkPerformed.setName(updName);
            typeOfWorkPerformedRepos.save(typeOfWorkPerformed);
            return "Изменение прошло успешно!";
        } else return "Изменение не произошло!";
    }

    @Override
    public String delete(Integer delIdTypeOfWorkPerformed) {
        TypeOfWorkPerformed typeOfWorkPerformed = typeOfWorkPerformedRepos.findById(delIdTypeOfWorkPerformed).orElse(null);
        if (typeOfWorkPerformed == null) {
            return "Данный вид работы не найден!";
        } else {
            typeOfWorkPerformedRepos.delete(typeOfWorkPerformed);
            return "Вид работы успешно удалён!";
        }
    }

    @Override
    public String add(String name) {
        if (name != null && !name.isEmpty()) {
            TypeOfWorkPerformed typeOfWorkPerformed = new TypeOfWorkPerformed(name);
            TypeOfWorkPerformed c = typeOfWorkPerformedRepos.findByName(typeOfWorkPerformed.getName());
            if (c == null) {
                typeOfWorkPerformedRepos.save(typeOfWorkPerformed);
            } else return "Данный вид работы уже есть в базе данных!";
        } else return "Поле с именем заполненно не корректно!";
        return "Новый вид работы успешно добавлен!";
    }

    @Override
    public TypeOfWorkPerformed findById(Integer id) {
        return typeOfWorkPerformedRepos.findById(id).orElse(null);
    }
}
