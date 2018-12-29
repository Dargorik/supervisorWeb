package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.Role;
import supervisorweb.domain.User;

import java.util.List;


public interface UserRepos extends JpaRepository<User, Integer> {

    User findByUsername(String login);

    @Override
    @Query(value = "select u from User u group by u.firstName, u.lastName, u.username")
    List<User> findAll();

    List<User> findByFirstNameLike(String firstName);

    List<User> findByLastNameLike(String lastName);

    List<User> findByFirstNameLikeAndLastNameLike(String firstName, String lastName);

    User findByFirstNameAndLastName(String firstName, String lastName);

    User findByFirstNameAndLastNameOrUsername(String firstName, String lastName, String username);

    User findByFirstNameAndLastNameAndUsername(String firstName, String lastName, String username);

    @Query(value = "select u from User u where (u.firstName=:firstName and u.lastName=:lastName or u.username=:username) and not u.idusers=:id")
    User findByFirstNameAndLastNameOrUsernameAndNotId(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("username") String username, @Param("id") Integer id);

    List<User> findByRoles(Role role);

    User findByActivationCode(String code);
}
