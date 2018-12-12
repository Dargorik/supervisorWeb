package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import supervisorweb.domain.Address;
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
    public String update(Integer updId, String firstName, String lastName, String username, String password, Integer idPosition) {
        User user;
        try {

            if(firstName==null||firstName.isEmpty()||
                    lastName==null||lastName.isEmpty()||
                    username==null||username.isEmpty()||
                    password==null||password.isEmpty())
                return "Введенны некорректные данные";
            user=userRepo.findByUsername(username);
            if(user!=null)
                if(!user.getIdusers().equals(updId))
                    return "Сотрудник с таким Логином уже есть";
            user=userRepo.findByFirstNameAndLastNameAndUsername(firstName, lastName, username);
            if(user!=null)
                if(!user.getIdusers().equals(updId))
                return "Пользователь с такой Фамилией, Менем и Логином уже есть";
            user=userRepo.findById(updId).orElse(null);
            if(user==null)
                return "Пользователь с таким id  не найден";
            Position position=positionRepos.findById(idPosition).orElse(null);
            if (position==null)
                return "Указанная должность не найдена!";
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setPassword(password);
            user.setPosition(position);
            userRepo.save(user);
            return "Изменение прошло успешно!";
        }
        catch (NullPointerException e){
            return "Введенны некорректные данные";
        }
    }

    @Override
    public String delete(Integer delId) {
        User user;
        try {
            user=userRepo.findById(delId).orElse(null);
            if(user==null)
                throw new NullPointerException();
        }catch (NullPointerException e){
            return "Данный сотрудник не удлалён!";
        }
        userRepo.delete(user);
        return "Сотрудник успешно удлалён!";
    }

    @Override
    public String add(String firstName, String lastName, String username, String password, Integer idPosition) {
        if(firstName==null||firstName.isEmpty()||
                lastName==null||lastName.isEmpty()||
                username==null||username.isEmpty()||
                password==null||password.isEmpty())
            return "Введенны некорректные данные";
        User user=userRepo.findByUsername(username);
        if(user!=null)
            return "Сотрудник с Логином уже есть";
        user=userRepo.findByFirstNameAndLastNameAndUsername(username, password, username);
        if(user!=null)
            return "Сотрудник с такой Фамилией, Менем и Логином уже есть";
        Position position=positionRepos.findById(idPosition).orElse(null);
        if (position==null)
            return "Указанный сотрудник не найдена!";
        user=new User(firstName,lastName,username, password, position);
        user.setActiv(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "Новый сотрудник добавлен";
    }

    @Override
    public void checkAdminProfile() {
        User user=userRepo.findByRoles(Role.ADMIN);
        if(user==null){
            user=new User();
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
        return userRepo.findByFirstNameAndLastName(firstName,lastName);
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
