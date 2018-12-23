package supervisorweb.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class TypeOfWork {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idTypeOfWork;
    private String name;

    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "typeOfWork")
    private List<PositionDuties> positionDuties;
    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "typeOfWork")
    private List<ListTypesInPerfomedWork> listTypesInPerfomedWork;
    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "typeOfWork")
    private List<LastCompletedDateAddress> lastComletedDateAddresses;

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
