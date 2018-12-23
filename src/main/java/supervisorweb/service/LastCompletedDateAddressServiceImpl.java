package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.Address;
import supervisorweb.domain.LastCompletedDateAddress;
import supervisorweb.domain.TypeOfWork;
import supervisorweb.repos.LastCompletedDateAddressRepos;
import supervisorweb.repos.TypeOfWorkRepos;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LastCompletedDateAddressServiceImpl implements LastCompletedDateAddressService {
    @Autowired
    private LastCompletedDateAddressRepos lastCompletedDateAddressRepos;
    @Autowired
    private TypeOfWorkRepos typeOfWorkRepos;

    @Override
    public List<LastCompletedDateAddress> findAllAddress() {
        return lastCompletedDateAddressRepos.findAllAddress();
    }

    @Override
    public List<LastCompletedDateAddress> findAllAddressByTypeWork(TypeOfWork typeOfWork) {
        return lastCompletedDateAddressRepos.findAllAddressByTypeWork(typeOfWork);
    }

    @Override
    public List<LastCompletedDateAddress> findRelevance(Integer idTypeOfWorkPerformed) {
        List<LastCompletedDateAddress> lastCompletedDateAddresses = new ArrayList<>();
        for(LastCompletedDateAddress lastCompletedDateAddress: lastCompletedDateAddressRepos.findAllAddressByTypeWork(typeOfWorkRepos.findById(idTypeOfWorkPerformed).orElse(null))){
            System.out.println(lastCompletedDateAddress.getLastData());
            if(lastCompletedDateAddress.getLastData()!=null){
                System.out.println("11");
                if(Integer.parseInt(lastCompletedDateAddress.getDate())>lastCompletedDateAddress.getAddress().getPriorityList().getNumber())
                    lastCompletedDateAddresses.add(lastCompletedDateAddress);
            }
            else
                lastCompletedDateAddresses.add(lastCompletedDateAddress);
        }
        return lastCompletedDateAddresses;
    }
}
