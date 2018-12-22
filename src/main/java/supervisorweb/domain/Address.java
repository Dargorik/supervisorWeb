package supervisorweb.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idAddress;

    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST})
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST})
    @JoinColumn(name = "street_id")
    private Street street;

    private String houseNumber;
    private Integer numberFloors;
    private Integer numberEntrances;

    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST})
    @JoinColumn(name = "priority_id")
    private PriorityList priorityList;

    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST})
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "address")
    private List<CompletedWork> completedWork;

    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "address")
    private List<WorksBasket> WorksBasket;

    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "address")
    private List<LastComletedDateAddress> lastComletedDateAddresses;

    public Address(){}

    public Address(City city, Street street, String houseNumber, Integer numberFloors, Integer numberEntrances, PriorityList priorityList, Region region){
        this.city=city;
        this.street=street;
        this.houseNumber=houseNumber;
        this.numberFloors=numberFloors;
        this.numberEntrances=numberEntrances;
        this.priorityList=priorityList;
        this.region=region;
    }

    public Integer getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Integer idAddress) {
        this.idAddress = idAddress;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getNumberFloors() {
        return numberFloors;
    }

    public void setNumberFloors(Integer numberFloors) {
        this.numberFloors = numberFloors;
    }

    public Integer getNumberEntrances() {
        return numberEntrances;
    }

    public void setNumberEntrances(Integer numberEntrances) {
        this.numberEntrances = numberEntrances;
    }

    public PriorityList getPriorityList() {
        return priorityList;
    }

    public void setPriorityList(PriorityList priorityList) {
        this.priorityList = priorityList;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
