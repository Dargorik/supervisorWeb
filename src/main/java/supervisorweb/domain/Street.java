package supervisorweb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Street {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idStreet;
    private String name;

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
