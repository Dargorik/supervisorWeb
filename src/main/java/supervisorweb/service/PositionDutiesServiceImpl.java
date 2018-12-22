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
import java.util.stream.Collectors;

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
        return positionDutiesRepos.findAll().stream()
                .sorted((x, y) -> x.getTypeOfWork().getName().compareTo(y.getTypeOfWork().getName()))
                .sorted((x, y) -> x.getPosition().getName().compareTo(y.getPosition().getName()))
                .collect(Collectors.toList());
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
                    return "This component already exists!";
            positionDuties  = positionDutiesRepos.findById(updIdPositionDuties).orElse(null);
            positionDuties.setPosition(position);
            positionDuties.setTypeOfWork(typeOfWork);
            positionDutiesRepos.save(positionDuties);
            return "Successful update record!";
        }
        catch (NullPointerException e){
            return "Invalid input!";
        }
    }

    @Override
    public String delete(Integer delIdPositionDuties) {
        PositionDuties positionDuties = positionDutiesRepos.findById(delIdPositionDuties).orElse(null);
        if (positionDuties == null) {
            return "This component does not exist!";
        } else {
            positionDutiesRepos.delete(positionDuties);
            return "Successful delete record!";
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
        } else return "This component already exists!";
        return "Successful add record!";
    }

    @Override
    public PositionDuties findById(Integer updId) {
        return positionDutiesRepos.findById(updId).orElse(null);
    }
}
