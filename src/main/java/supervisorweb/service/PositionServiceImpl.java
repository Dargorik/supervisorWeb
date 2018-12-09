package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.Position;
import supervisorweb.repos.PositionRepos;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionRepos positionRepos;

    @Override
    public Position findByName(String name) {
        return positionRepos.findByName(name);
    }

    @Override
    public List<Position> findAll() {
        return positionRepos.findAll().stream().sorted((x, y) -> x.getName().compareTo(y.getName())).collect(Collectors.toList());
    }

    @Override
    public String update(Integer updIdPosition, String updName) {
        if (updName == null || updName.isEmpty())
            return "Поле с именем заполненно не корректно!";
        Position position  = positionRepos.findById(updIdPosition).orElse(null);
        if (position != null) {
            position.setName(updName);
            positionRepos.save(position);
            return "Изменение прошло успешно!";
        } else return "Изменение не произошло!";
    }

    @Override
    public String delete(Integer delIdPosition) {
        Position position = positionRepos.findById(delIdPosition).orElse(null);
        if (position == null) {
            return "Данная должность не найден!";
        } else {
            positionRepos.delete(position);
            return "Должность успешно удалён!";
        }
    }

    @Override
    public String add(String name) {
        if (name != null && !name.isEmpty()) {
            Position position = new Position(name);
            Position c = positionRepos.findByName(position.getName());
            if (c == null) {
                positionRepos.save(position);
            } else return "Данная должность уже есть в базе данных!";
        } else return "Поле с именем заполненно не корректно!";
        return "Новая должность успешно добавлена!";
    }

    @Override
    public Position findById(Integer updId) {
        return positionRepos.findById(updId).orElse(null);
    }
}
