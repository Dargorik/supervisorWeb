package supervisorweb.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class PriorityList {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idPriorityList;
    private String name;
    private Integer number;


    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "priorityList")
    private List<Address> addresses;

    public PriorityList(String name, Integer number){
        this.name=name;
        this.number=number;
    }

    public PriorityList(){}

    public Integer getIdPriorityList() {
        return idPriorityList;
    }

    public void setIdPriorityList(Integer idPosition) {
        this.idPriorityList = idPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
