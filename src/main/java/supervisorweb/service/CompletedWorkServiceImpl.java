package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.*;
import supervisorweb.repos.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CompletedWorkServiceImpl implements CompletedWorkService {
    @Autowired
    private CompletedWorkRepos completedWorkRepos;
    @Autowired
    private AddressRepos addressRepos;
    @Autowired
    private UserRepos userRepos;
    @Autowired
    private TypeOfWorkPerformedRepos typeOfWorkPerformedRepos;

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
        if(numberCompletedEntrances==null||numberCompletedEntrances.isEmpty())
            return "Введенны некорректные данные1";
        Integer number=Integer.parseInt(numberCompletedEntrances);
        if(number==null)
            return "Введенны некорректные данные";
        User user=userRepos.findById(idUsers).orElse(null);
        Address address=addressRepos.findById(idAddress).orElse(null);
        TypeOfWorkPerformed typeOfWorkPerformed=typeOfWorkPerformedRepos.findById(idTypeOfWorkPerformed).orElse(null);
        if (user==null||address==null||typeOfWorkPerformed==null)
            return "Ошибка!";
        if(number>address.getNumberEntrances()||number<1)
            return "Указано неверное кол-во подъездов";
        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        long time = 0;
        time = date.getTime();
        Timestamp timestamp=new Timestamp(time);
        CompletedWork completedWork=new CompletedWork(user, address, number, typeOfWorkPerformed, comment, timestamp);
        completedWorkRepos.save(completedWork);
        return "Новый запись добавлена";
    }
}
