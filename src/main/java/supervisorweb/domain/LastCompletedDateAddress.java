package supervisorweb.domain;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
public class LastCompletedDateAddress {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST})
    @JoinColumn(name = "typeOfWork_id")
    private TypeOfWork typeOfWork;
    private Timestamp lastData;

    public String getDate(){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = formatter.parse(formatter.format(new Date()));
        } catch (ParseException e) {
            return "none!";
        }
        //Timestamp timestamp = new Timestamp(date.getTime());
        //return  lastData==null?"none":(formatter.format(lastData));
        return  lastData==null?"none":(String.valueOf((date.getTime()-lastData.getTime())/86400000));
    }

    public LastCompletedDateAddress(){}

    public LastCompletedDateAddress(Address address, TypeOfWork typeOfWork, Timestamp lastData) {
        this.address = address;
        this.typeOfWork = typeOfWork;
        this.lastData = lastData;
    }

    public LastCompletedDateAddress(Address address, TypeOfWork typeOfWork) {
        this.address = address;
        this.typeOfWork = typeOfWork;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public TypeOfWork getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(TypeOfWork typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public Timestamp getLastData() {
        return lastData;
    }

    public void setLastData(Timestamp lastData) {
        this.lastData = lastData;
    }
}
