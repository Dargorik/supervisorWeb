package supervisorweb.domain;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import supervisorweb.domain.Role;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idusers;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
   // @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean activ;

    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST})
    @JoinColumn(name = "position_id")
    private Position position;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "user")
    private List<CompletedWork> completedWork;
    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "user")
    private List<UserRegions> userRegions;
    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy = "user")
    private List<WorksBasket> WorksBasket;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName, String username, String password, Position position) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.username=username;
        this.password=password;
        this.position=position;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActiv();
    }

    public void setUsername(String Username) {
        this.username = Username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passowrd) {
        this.password = passowrd;
    }

    public boolean isActiv() {
        return activ;
    }

    public void setActiv(boolean activ) {
        this.activ = activ;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }



    public Integer getIdusers() {
        return idusers;
    }

    public void setIdusers(Integer idusers) {
        this.idusers = idusers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
/*
    public Set<RegionsByUsers> getRegionsByUsers() {
        return regionsByUsers;
    }

    public void setRegionsByUsers(Set<RegionsByUsers> regionsByUsers) {
        this.regionsByUsers = regionsByUsers;
    }*/
}
