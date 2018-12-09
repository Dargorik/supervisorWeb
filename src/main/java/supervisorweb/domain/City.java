package supervisorweb.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class City  {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idCity;
    private String name;

    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "city")
    private List<Address> addresses;

    public City(String name){
        this.name=name;
    }

    public City(){}

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
