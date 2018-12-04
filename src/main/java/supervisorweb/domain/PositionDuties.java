package supervisorweb.domain;


import javax.persistence.*;

@Entity
public class PositionDuties {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idPositionDuties;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position position;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeOfWork_id")
    private TypeOfWork typeOfWork;

    public PositionDuties(){}

    public PositionDuties(Position position, TypeOfWork typeOfWork){
        this.position=position;
        this.typeOfWork=typeOfWork;
    }

    public Integer getIdPositionDuties() {
        return idPositionDuties;
    }

    public void setIdPositionDuties(Integer idPositionDuties) {
        this.idPositionDuties = idPositionDuties;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public TypeOfWork getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(TypeOfWork typeOfWork) {
        this.typeOfWork = typeOfWork;
    }
}
