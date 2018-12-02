package supervisorweb.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PriorityList {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idPriorityList;
    private String name;
    private Integer number;

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
