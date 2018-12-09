package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import supervisorweb.domain.Position;
import supervisorweb.domain.TypeOfWork;
import supervisorweb.repos.PositionRepos;

import java.util.List;

public interface PositionService {
    Position findByName(String name);

    List<Position> findAll();


    String update(Integer updIdPosition, String updName);

    String delete(Integer delIdPosition);

    String add(String name);

    Position findById(Integer updId);
}
