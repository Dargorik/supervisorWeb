package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import supervisorweb.domain.Position;
import supervisorweb.domain.Role;
import supervisorweb.domain.User;
import supervisorweb.repos.PositionRepos;
import supervisorweb.repos.UserRepos;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepos userRepo;
    @Autowired
    private PositionRepos positionRepos;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    @Override
    public User findById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> findAllUser() {
        return userRepo.findByRoles(Role.USER);
    }

    @Override
    public String update(Integer updId, String firstName, String lastName, String username, String password, Integer idPosition, Boolean activ) {
        User user;
        if (firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty() ||
                username == null || username.isEmpty() ||
                password == null || password.isEmpty())
            return "Invalid input!";
        if (userRepo.findByFirstNameAndLastNameOrUsernameAndNotId(firstName, lastName, username, updId) != null)
            return "This component already exists!";
        user = userRepo.findById(updId).orElse(null);
        Position position = positionRepos.findById(idPosition).orElse(null);
        if (user == null || position == null)
            return "This component does not exist!";
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setPosition(position);
        user.setActiv(activ);
        userRepo.save(user);
        return "Successful update record!";
    }

    @Override
    public String delete(Integer delId) {
        User user=userRepo.findById(delId).orElse(null);;
        if(user==null)
            return "This component does not exist!";
        else{
            userRepo.delete(user);
        return "Successful delete record!";
        }
    }

    @Override
    public String add(String firstName, String lastName, String username, String password, Integer idPosition) {
        if (firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty() ||
                username == null || username.isEmpty() ||
                password == null || password.isEmpty())
            return "Invalid input!";
        if(userRepo.findByFirstNameAndLastNameOrUsername(firstName, lastName, username)!=null)
            return "This component already exists!";
        Position position = positionRepos.findById(idPosition).orElse(null);
        if (position == null)
            return "Invalid input!";
        User user = new User(firstName, lastName, username, password, position);
        user.setActiv(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "Successful add record!";
    }

    @Override
    public void checkAdminProfile() {
        int i = userRepo.findByRoles(Role.ADMIN).size();
        if (i == 0) {
            User user = new User();
            user.setFirstName("Admin");
            user.setLastName("Admin");
            user.setUsername("admin");
            user.setPassword("admin");
            user.setRoles(Collections.singleton(Role.ADMIN));
            user.setActiv(true);
            userRepo.save(user);
        }
    }

    @Override
    public User findByFirstNameAndLastName(String firstName, String lastName) {
        return userRepo.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User findByFirstNameAndLastNameAndUsername(String firstName, String lastName, String username) {
        return userRepo.findByFirstNameAndLastNameAndUsername(firstName, lastName, username);
    }
}
