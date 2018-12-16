package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.Position;
import supervisorweb.repos.PositionRepos;

import java.util.List;

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
        return positionRepos.findAll();
    }

    @Override
    public String update(Integer updId, String updName) {
        if (updName == null || updName.isEmpty())
            return "Invalid input!";
        Position position  = positionRepos.findById(updId).orElse(null);
        if (position != null) {
            position.setName(updName);
            positionRepos.save(position);
            return "Successful update record!";
        } else return "This component already exists!";
    }

    @Override
    public String delete(Integer delId) {
        Position position = positionRepos.findById(delId).orElse(null);
        if (position == null) {
            return "This component already exists!";
        } else {
            positionRepos.delete(position);
            return "Successful delete record!";
        }
    }

    @Override
    public String add(String name) {
        if (name != null && !name.isEmpty()) {
            Position position = new Position(name);
            Position c = positionRepos.findByName(position.getName());
            if (c == null) {
                positionRepos.save(position);
            } else return "This component already exists!";
        } else return "Invalid input!";
        return "Successful add record!";
    }

    @Override
    public Position findById(Integer updId) {
        return positionRepos.findById(updId).orElse(null);
    }
}
