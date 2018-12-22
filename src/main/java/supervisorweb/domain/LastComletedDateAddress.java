package supervisorweb.domain;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.sql.Timestamp;

@Entity
public class LastComletedDateAddress {
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

    public LastComletedDateAddress(){}

    public LastComletedDateAddress(Address address, TypeOfWork typeOfWork, Timestamp lastData) {
        this.address = address;
        this.typeOfWork = typeOfWork;
        this.lastData = lastData;
    }

    public LastComletedDateAddress(Address address, TypeOfWork typeOfWork) {
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
