package supervisorweb.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class TypeOfWorkPerformed {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idTypeOfWorkPerformed;
    private String name;

    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "typeOfWorkPerformed")
    private List<CompletedWork> completedWork;
    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "typeOfWorkPerformed")
    private List<ListTypesInPerfomedWork> listTypesInPerfomedWork;

    public TypeOfWorkPerformed(){};

    public TypeOfWorkPerformed(String name){
        this.name=name;
    }

    public Integer getIdTypeOfWorkPerformed() {
        return idTypeOfWorkPerformed;
    }

    public void setIdTypeOfWorkPerformed(Integer idTypeOfWorkPerformed) {
        this.idTypeOfWorkPerformed = idTypeOfWorkPerformed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
