package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.Street;
import supervisorweb.repos.StreetRepos;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    StreetRepos streetRepos;

    @Override
    public Street findByName(String name) {

        return streetRepos.findByName(name);
    }

    @Override
    public List<Street> findAll() {
        return streetRepos.findAll().stream().sorted((x,y)->x.getName().compareTo(y.getName())).collect(Collectors.toList());
    }

    @Override
    public String update(String updIdCity, String updName) {
        Integer id = Integer.parseInt(updIdCity);
        if (id == null || updName == null || updName.isEmpty())
            return "Поле с именем заполненно не корректно!";
        Street street = streetRepos.findById(id).orElse(null);
        if (street != null) {
            street.setName(updName);
            streetRepos.save(street);
            return "Изменение прошло успешно!";
        }
        else return "Изменение не произошло!";
    }

    @Override
    public String delete(String delIdCity) {
        Integer id = Integer.parseInt(delIdCity);
        Street street = streetRepos.findById(id).orElse(null);
        if (street == null) {
            return "Данная улица не найдена!";
        } else {
            streetRepos.delete(street);
            return "Улица успешно удалёна!";
        }
    }

    @Override
    public String add(String name) {
        if (name != null && !name.isEmpty()) {
            Street street = new Street(name);
            Street s = streetRepos.findByName(street.getName());
            if (s == null) {
                streetRepos.save(street);
            } else return "Данная улица уже есть в базе данных!";
        } else return "Поле с именем заполненно не корректно!";
        return "Новая улица успешно добавлена!";
    }

    @Override
    public Street findById(String updId) {
        return streetRepos.findById(Integer.parseInt(updId)).orElse(null);
    }
}
