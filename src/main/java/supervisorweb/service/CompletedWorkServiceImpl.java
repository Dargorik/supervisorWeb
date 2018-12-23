package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.*;
import supervisorweb.repos.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompletedWorkServiceImpl implements CompletedWorkService {
    @Autowired
    private WorksBasketRepos worksBasketRepos;
    @Autowired
    private CompletedWorkRepos completedWorkRepos;
    @Autowired
    private AddressRepos addressRepos;
    @Autowired
    private UserRepos userRepos;
    @Autowired
    private TypeOfWorkPerformedRepos typeOfWorkPerformedRepos;
    @Autowired
    private LastCompletedDateAddressRepos lastCompletedDateAddressRepos;
    @Autowired
    private ListTypesInPerfomedWorkRepos listTypesInPerfomedWorkRepos;

    @Override
    public List<CompletedWork> findAll() {
        return completedWorkRepos.findAll();
    }

    @Override
    public List<CompletedWork> findById(Integer id) {
        return completedWorkRepos.findAll();
    }

    @Override
    public String add(Integer idUsers, Integer idAddress, String numberCompletedEntrances, Integer idTypeOfWorkPerformed, String comment) {
        if (numberCompletedEntrances == null || numberCompletedEntrances.isEmpty())
            return "Введенны некорректные данные1";
        Integer number = Integer.parseInt(numberCompletedEntrances);
        if (number == null)
            return "Введенны некорректные данные";
        User user = userRepos.findById(idUsers).orElse(null);
        Address address = addressRepos.findById(idAddress).orElse(null);
        TypeOfWorkPerformed typeOfWorkPerformed = typeOfWorkPerformedRepos.findById(idTypeOfWorkPerformed).orElse(null);
        if (user == null || address == null || typeOfWorkPerformed == null)
            return "Ошибка!";
        if (number > address.getNumberEntrances() || number < 1)
            return "Указано неверное кол-во подъездов";
        Date date = new Date();
        long time = 0;
        time = date.getTime();
        Timestamp timestamp = new Timestamp(time);
        CompletedWork completedWork = new CompletedWork(user, address, number, typeOfWorkPerformed, comment, timestamp);
        completedWorkRepos.save(completedWork);
        return "Новый запись добавлена";
    }


    @Override
    public String report(User user) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = formatter.parse(formatter.format(new Date()));
        } catch (ParseException e) {
            return "Error!";
        }
        Timestamp timestamp = new Timestamp(date.getTime());
        for (WorksBasket worksBasket : worksBasketRepos.findByUser(user)) {
            CompletedWork completedWork = new CompletedWork(worksBasket.getUser(),
                    worksBasket.getAddress(),
                    worksBasket.getNumberCompletedEntrances(),
                    worksBasket.getTypeOfWorkPerformed(),
                    worksBasket.getComment(),
                    timestamp);
            completedWorkRepos.save(completedWork);
            addLastCompletedDataAddress(worksBasket.getAddress(), worksBasket.getTypeOfWorkPerformed(), timestamp);
        }
        worksBasketRepos.deleteAll();
        return "Successful!";
    }

    @Override
    public List<CompletedWork> findByData(Date frome, Date to) {
        List<CompletedWork> completedWorksList = completedWorkRepos.findAll();
        List<CompletedWork> completedWorks = completedWorkRepos.findAll();
        for (CompletedWork completedWork : completedWorksList) {
            if (frome != null && completedWork.getTimestamp_send().getTime() < frome.getTime())
                completedWorks.remove(completedWork);
            if (to != null && completedWork.getTimestamp_send().getTime() > to.getTime())
                completedWorks.remove(completedWork);
        }
        return completedWorks;
    }

    @Override
    public String delete(Integer delId) {
        CompletedWork completedWork = completedWorkRepos.findById(delId).orElse(null);
        if (completedWork == null) {
            return "This component does not exist!";
        } else {
            completedWorkRepos.delete(completedWork);
            return "Successful delete record!";
        }
    }

    public void addLastCompletedDataAddress(Address address, TypeOfWorkPerformed typeOfWorkPerformed, Timestamp lastData) {
        for (TypeOfWork typeOfWork : listTypesInPerfomedWorkRepos.findByTypeOfWorkPerformed(typeOfWorkPerformed).stream()
                .map(x -> x.getTypeOfWork())
                .collect(Collectors.toList())) {
            lastCompletedDateAddressRepos.findByAddressAndTypeOfWork(address, typeOfWork).setLastData(lastData);
        }
    }
}
