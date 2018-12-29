package supervisorweb.service;

import supervisorweb.domain.Address;
import supervisorweb.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    List<User> findAllUser();

    String update(Integer updId,
                  String firstName,
                  String lastName,
                  String username,
                  String password,
                  Integer position,
                  Boolean activ);

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

    String updateAdminProfile(Integer id, String firstName, String lastName, String email, String password);

    String resetAdminPasssword();

    boolean resetPassord(String code);
}
