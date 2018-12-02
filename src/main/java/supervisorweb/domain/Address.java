package supervisorweb.domain;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "street_id")
    private Street street;

    private String houseNumber;
    private Integer numberFloors;
    private Integer numberEntrances;

    public Address(){}

    public Address(City city, Street street, String houseNumber, Integer numberFloors, Integer numberEntrances){
        this.city=city;
        this.street=street;
        this.houseNumber=houseNumber;
        this.numberFloors=numberFloors;
        this.numberEntrances=numberEntrances;
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
}
