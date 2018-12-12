package supervisorweb.service;

import supervisorweb.domain.Address;
import supervisorweb.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    String update(Integer updId,
                  String firstName,
                  String lastName,
                  String username,
                  String password,
                  Integer position);

    String delete(Integer delId);

    String add(String firstName,
               String lastName,
               String username,
               String password,
               Integer position);

    void checkAdminProfile();

    User findByFirstNameAndLastName(String firstName, String lastName);
    User findByUsername(String username);
    User findByFirstNameAndLastNameAndUsername(String firstName, String lastName, String username);
}
