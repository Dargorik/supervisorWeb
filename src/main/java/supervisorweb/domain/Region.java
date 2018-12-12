package supervisorweb.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Region {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idRegion;
    private String name;

    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "region")
    private List<Address> addresses;
    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "region")
    private List<UserRegions> userRegions;

    public Region(){}

    public Region(String name){
        this.name=name;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
