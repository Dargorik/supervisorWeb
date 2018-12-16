package supervisorweb.service;

import supervisorweb.domain.Position;

import java.util.List;

public interface PositionService {
    Position findByName(String name);

    List<Position> findAll();

    String update(Integer updId, String updName);

    String delete(Integer delId);

    String add(String name);

    Position findById(Integer updId);
}
