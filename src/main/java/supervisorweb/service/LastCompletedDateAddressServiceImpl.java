package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import supervisorweb.domain.Address;
import supervisorweb.domain.LastCompletedDateAddress;
import supervisorweb.domain.TypeOfWork;
import supervisorweb.repos.LastCompletedDateAddressRepos;
import supervisorweb.repos.TypeOfWorkRepos;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LastCompletedDateAddressServiceImpl implements LastCompletedDateAddressService {
    @Autowired
    private LastCompletedDateAddressRepos lastCompletedDateAddressRepos;
    @Autowired
    private TypeOfWorkRepos typeOfWorkRepos;

    @Override
    public Page<LastCompletedDateAddress> findAllAddress(String city,  String streetFilter, String regionFilter, String priorityFilter, Pageable pageable) {
        return lastCompletedDateAddressRepos.findAllAddress(city, streetFilter, regionFilter, priorityFilter, pageable);
    }

    @Override
    public Page<LastCompletedDateAddress> findAllAddressByTypeWork(TypeOfWork typeOfWork, String city,  String streetFilter, String regionFilter, String priorityFilter, Pageable pageable) {
        return lastCompletedDateAddressRepos.findAllAddressByTypeWork(typeOfWork, city, streetFilter, regionFilter, priorityFilter, pageable);
    }

    @Override
    public Page<LastCompletedDateAddress> findRelevance(TypeOfWork typeOfWork, String city, String streetFilter, String regionFilter, String priorityFilter, Pageable pageable) {
        List<Integer> lastCompletedDateAddresses = new ArrayList<>();
        for(LastCompletedDateAddress lastCompletedDateAddress: lastCompletedDateAddressRepos.findAllAddressByTypeWork2(typeOfWork)){
            if(lastCompletedDateAddress.getLastData()!=null){
                if(Integer.parseInt(lastCompletedDateAddress.getDate())>lastCompletedDateAddress.getAddress().getPriorityList().getNumber())
                    lastCompletedDateAddresses.add(lastCompletedDateAddress.getId());
            }
            else
                lastCompletedDateAddresses.add(lastCompletedDateAddress.getId());
        }
        return lastCompletedDateAddressRepos.findAllById(lastCompletedDateAddresses,pageable);
    }


}
