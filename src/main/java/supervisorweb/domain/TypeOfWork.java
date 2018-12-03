package supervisorweb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypeOfWork {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idTypeOfWork;
    private String name;

    public TypeOfWork(){}

    public TypeOfWork(String name){
        this.name=name;
    }

    public Integer getIdTypeOfWork() {
        return idTypeOfWork;
    }

    public void setIdTypeOfWork(Integer idTypeOfWork) {
        this.idTypeOfWork = idTypeOfWork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
