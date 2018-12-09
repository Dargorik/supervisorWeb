package supervisorweb.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idPosition;
    private String name;

    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "position")
    private List<User> users;

    public Position(String name){
        this.name=name;
    }

    public Position(){}

    public Integer getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(Integer idPosition) {
        this.idPosition = idPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
