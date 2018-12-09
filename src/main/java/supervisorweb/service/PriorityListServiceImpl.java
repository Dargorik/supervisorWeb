package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.City;
import supervisorweb.domain.PriorityList;
import supervisorweb.repos.PriorityListRepos;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriorityListServiceImpl implements PriorityListService {
    @Autowired
    PriorityListRepos priorityListRepos;

    @Override
    public PriorityList findByName(String name) {
        return priorityListRepos.findByName(name);
    }

    @Override
    public List<PriorityList> findAll() {
        return priorityListRepos.findAll().stream().sorted((x, y) -> x.getName().compareTo(y.getName())).collect(Collectors.toList());
    }

    @Override
    public String update(Integer updIdPriorityList, String updName, String updNumber) {
        Integer number = Integer.parseInt(updNumber);
        if (number == null || updName == null || updName.isEmpty())
            return "Поля заполненны не корректно!";
        PriorityList priorityList = priorityListRepos.findById(updIdPriorityList).orElse(null);
        if (priorityList != null) {
            priorityList.setName(updName);
            priorityList.setNumber(number);
            priorityListRepos.save(priorityList);
            return "Изменение прошло успешно!";
        } else return "Изменение не произошло!";
    }

    @Override
    public String delete(String delIdPriorityList) {
        Integer id = Integer.parseInt(delIdPriorityList);
        PriorityList priorityList = priorityListRepos.findById(id).orElse(null);
        if (priorityList == null) {
            return "Данная приоритетность не найден!";
        } else {
            priorityListRepos.delete(priorityList);
            return "Приоритетность успешно удалёнф!";
        }
    }

    @Override
    public String add(String name, String number) {
        Integer num = Integer.parseInt(number);
        if (num == null || name == null || name.isEmpty())
            return "Поля заполненны не корректно!";
        if (name != null && !name.isEmpty()) {
            PriorityList priorityList = new PriorityList(name, num);
            PriorityList pl = priorityListRepos.findByName(priorityList.getName());
            if (pl == null) {
                priorityListRepos.save(priorityList);
            } else return "Данная приоритетность уже есть в базе данных!";
        } else return "Поля заполненны не корректно!";
        return "Новая приоритетность успешно добавлен!";
    }

    @Override
    public PriorityList findById(Integer updId) {
        return priorityListRepos.findById(updId).orElse(null);
    }
}
