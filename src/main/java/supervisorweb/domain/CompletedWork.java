package supervisorweb.domain;


import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class CompletedWork {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idCompletedWork;
    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST})
    @JoinColumn(name = "user")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST})
    @JoinColumn(name = "address")
    private Address address;
    private Integer numberCompletedEntrances;
    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST})
    @JoinColumn(name = "typeOfWorkPerformed")
    private TypeOfWorkPerformed typeOfWorkPerformed;
    private String comment;
    private Timestamp timestamp_send;

    public String getDate(){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return  formatter.format(timestamp_send);
    }

    public CompletedWork(){}

    public CompletedWork(User user, Address address, Integer numberCompletedEntrances, TypeOfWorkPerformed typeOfWorkPerformed, String comment, Timestamp timestamp_send) {
        this.user = user;
        this.address = address;
        this.numberCompletedEntrances = numberCompletedEntrances;
        this.typeOfWorkPerformed = typeOfWorkPerformed;
        this.comment = comment;
        this.timestamp_send = timestamp_send;
    }



    public Integer getIdCompletedWork() {
        return idCompletedWork;
    }

    public void setIdCompletedWork(Integer idCompletedWork) {
        this.idCompletedWork = idCompletedWork;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getNumberCompletedEntrances() {
        return numberCompletedEntrances;
    }

    public void setNumberCompletedEntrances(Integer numberCompletedEntrances) {
        this.numberCompletedEntrances = numberCompletedEntrances;
    }

    public TypeOfWorkPerformed getTypeOfWorkPerformed() {
        return typeOfWorkPerformed;
    }

    public void setTypeOfWorkPerformed(TypeOfWorkPerformed typeOfWorkPerformed) {
        this.typeOfWorkPerformed = typeOfWorkPerformed;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getTimestamp_send() {
        return timestamp_send;
    }

    public void setTimestamp_send(Timestamp timestamp_send) {
        this.timestamp_send = timestamp_send;
    }
}
