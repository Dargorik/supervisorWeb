package supervisorweb.domain;

import javax.persistence.*;

@Entity
public class UserRegions {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idUserRegions;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    private Region region;

    public UserRegions(){}

    public UserRegions(User user, Region region){
        this.user=user;
        this.region=region;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Integer getIdUserRegions() {
        return idUserRegions;
    }

    public void setIdUserRegions(Integer idUserRegions) {
        this.idUserRegions = idUserRegions;
    }
}
