package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.*;
import supervisorweb.repos.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorksBasketServiceImpl implements WorksBasketService {
    @Autowired
    private WorksBasketRepos worksBasketRepos;
    @Autowired
    private AddressRepos addressRepos;
    @Autowired
    private UserRepos userRepos;
    @Autowired
    private TypeOfWorkPerformedRepos typeOfWorkPerformedRepos;
    @Autowired
    private TypeOfWorkRepos typeOfWorkRepos;
    @Autowired
    private LastCompletedDateAddressRepos lastCompletedDateAddressRepos;

    @Override
    public List<WorksBasket> findAll() {
        return worksBasketRepos.findAll().stream()
                .sorted((x,y)->x.getAddress().getHouseNumber().compareTo(y.getAddress().getHouseNumber()))
                .sorted((x,y)->x.getAddress().getStreet().getName().compareTo(y.getAddress().getStreet().getName()))
                .sorted((x,y)->x.getAddress().getCity().getName().compareTo(y.getAddress().getCity().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorksBasket> findById(Integer id) {
        return worksBasketRepos.findAll();
    }

    @Override
    public String add(Integer idUsers, Integer idAddress, String numberCompletedEntrances, Integer idTypeOfWorkPerformed, String comment) {
        if (numberCompletedEntrances == null || numberCompletedEntrances.isEmpty())
            return "Invalid input!";
        Integer number = Integer.parseInt(numberCompletedEntrances);
        if (number == null)
            return "Invalid input!";
        User user = userRepos.findById(idUsers).orElse(null);
        Address address = addressRepos.findById(idAddress).orElse(null);
        TypeOfWorkPerformed typeOfWorkPerformed = typeOfWorkPerformedRepos.findById(idTypeOfWorkPerformed).orElse(null);
        if (user == null || address == null || typeOfWorkPerformed == null)
            return "Invalid input!";
        if (number > address.getNumberEntrances() || number < 1)
            return "Invalid input!";
        WorksBasket worksBasket = new WorksBasket(user, address, number, typeOfWorkPerformed, comment);
        worksBasketRepos.save(worksBasket);
        return "Successful add record!";
    }

    @Override
    public List<WorksBasket> findByUser(User user) {
        return worksBasketRepos.findByUser(user);
    }

    @Override
    public String delete(Integer delId) {
        WorksBasket worksBasket = worksBasketRepos.findById(delId).orElse(null);
        if (worksBasket == null) {
            return "This component does not exist!";
        } else {
            worksBasketRepos.delete(worksBasket);
            return "Successful delete record!";
        }
    }

    @Override
    public List<Address> findRelevanceOtherAddresses(User user, Integer idTypeOfWorkPerformed) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        List<Address> addresses = new ArrayList<>();
        Date date;
        try {
            date = formatter.parse(formatter.format(new Date()));
        } catch (ParseException e) {
            return null;
        }
        for (TypeOfWork typeOfWork :typeOfWorkRepos.findTypeOfWorkByTypeOfWorkPerformed(typeOfWorkPerformedRepos.findById(idTypeOfWorkPerformed).orElse(null)))
        {
            for (Address address : addressRepos.findByUserRegions(user)) {
                if(!addresses.contains(address)) {
                    Timestamp timestamp = lastCompletedDateAddressRepos.findByAddressAndTypeOfWork(address, typeOfWork).getLastData();
                    if (timestamp != null) {
                        if ((date.getTime() - lastCompletedDateAddressRepos.findByAddressAndTypeOfWork(address, typeOfWork).getLastData().getTime()) / 86400000 <= address.getPriorityList().getNumber())
                            addresses.add(address);
                    } else
                        addresses.add(address);
                }
            }
        }
        return addresses;
    }


    @Override
    public List<Address> findOtherAddresses(User user) {
        return addressRepos.findByUserRegions(user);
    }
}
