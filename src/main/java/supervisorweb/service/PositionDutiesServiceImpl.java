package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.Position;
import supervisorweb.domain.PositionDuties;
import supervisorweb.domain.TypeOfWork;
import supervisorweb.repos.PositionDutiesRepos;
import supervisorweb.repos.PositionRepos;
import supervisorweb.repos.TypeOfWorkRepos;

import java.util.List;

@Service
public class PositionDutiesServiceImpl implements PositionDutiesService {
    @Autowired
    private PositionDutiesRepos positionDutiesRepos;
    @Autowired
    private PositionRepos positionRepos;
    @Autowired
    private TypeOfWorkRepos typeOfWorkRepos;

    @Override
    public PositionDuties findByPostionAndTypeOfWork(Integer idPosition, Integer idTypeOfWork) {
        Position position=positionRepos.findById(idPosition).orElse(null);
        TypeOfWork typeOfWork=typeOfWorkRepos.findById(idTypeOfWork).orElse(null);
        return positionDutiesRepos.findByPositionAndTypeOfWork(position, typeOfWork);
    }

    @Override
    public List<PositionDuties> findAll() {
        return positionDutiesRepos.findAll();
    }

    @Override
    public String update(Integer updIdPositionDuties, Integer updIdPosition, Integer updIdTypeOfWork) {
        PositionDuties positionDuties;
        try {
            Position position=positionRepos.findById(updIdPosition).orElse(null);
            TypeOfWork typeOfWork=typeOfWorkRepos.findById(updIdTypeOfWork).orElse(null);
            positionDuties=positionDutiesRepos.findByPositionAndTypeOfWork(position, typeOfWork);
            if(positionDuties!=null)
                if (!positionDuties.getIdPositionDuties().equals(updIdPositionDuties))
                    return "Изменение не произошло!";
            positionDuties  = positionDutiesRepos.findById(updIdPositionDuties).orElse(null);
            positionDuties.setPosition(position);
            positionDuties.setTypeOfWork(typeOfWork);
            positionDutiesRepos.save(positionDuties);
            return "Изменение прошло успешно!";
        }
        catch (NullPointerException e){
           return "Введенны некорректные данные";
        }
    }

    @Override
    public String delete(Integer delIdPositionDuties) {
        PositionDuties positionDuties = positionDutiesRepos.findById(delIdPositionDuties).orElse(null);
        if (positionDuties == null) {
            return "Данная связка не найден!";
        } else {
            positionDutiesRepos.delete(positionDuties);
            return "Связка успешно удалён!";
        }
    }

    @Override
    public String add(Integer idPosition, Integer idTypeOfWork) {
        Position position=positionRepos.findById(idPosition).orElse(null);
        TypeOfWork typeOfWork=typeOfWorkRepos.findById(idTypeOfWork).orElse(null);
        PositionDuties positionDutiessn = new PositionDuties(position, typeOfWork);
        PositionDuties pd = positionDutiesRepos.findByPositionAndTypeOfWork(position,typeOfWork);
        if (pd == null) {
            positionDutiesRepos.save(positionDutiessn);
        } else return "Данная связка уже есть в базе данных!";
        return "Новый тип работы разрешенный должности добавлен!";
    }

    @Override
    public PositionDuties findById(Integer updId) {
        return positionDutiesRepos.findById(updId).orElse(null);
    }
}
