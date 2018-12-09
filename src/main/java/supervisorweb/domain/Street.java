package supervisorweb.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Street {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idStreet;
    private String name;

    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "street")
    private List<Address> addresses;

    public Street(){}

    public Street(String name) {
        this.name=name;
    }

    public Integer getIdStreet() {
        return idStreet;
    }

    public void setIdStreet(Integer idStret) {
        this.idStreet = idStret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
