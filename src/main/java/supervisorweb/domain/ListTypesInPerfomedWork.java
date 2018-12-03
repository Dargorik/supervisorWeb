package supervisorweb.domain;

import javax.persistence.*;

@Entity
public class ListTypesInPerfomedWork {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idListTypesInPerfomedWork;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeOfWorkPerformed_id")
    private TypeOfWorkPerformed typeOfWorkPerformed;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeOfWork_id")
    private TypeOfWork typeOfWork;

    public ListTypesInPerfomedWork(){}

    public ListTypesInPerfomedWork(TypeOfWorkPerformed typeOfWorkPerformed, TypeOfWork typeOfWork){
        this.typeOfWorkPerformed=typeOfWorkPerformed;
        this.typeOfWork=typeOfWork;
    }

    public Integer getIdListTypesInPerfomedWork() {
        return idListTypesInPerfomedWork;
    }

    public void setIdListTypesInPerfomedWork(Integer idListTypesInPerfomedWork) {
        this.idListTypesInPerfomedWork = idListTypesInPerfomedWork;
    }

    public TypeOfWorkPerformed getTypeOfWorkPerformed() {
        return typeOfWorkPerformed;
    }

    public void setTypeOfWorkPerformed(TypeOfWorkPerformed typeOfWorkPerformed) {
        this.typeOfWorkPerformed = typeOfWorkPerformed;
    }

    public TypeOfWork getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(TypeOfWork typeOfWork) {
        this.typeOfWork = typeOfWork;
    }
}
