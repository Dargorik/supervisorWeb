package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.User;

import java.util.List;


public interface UserRepos extends JpaRepository<User, Long> {

    User findByUsername(String login);

    List<User> findByFirstNameLike(String firstName);
    List<User> findByLastNameLike(String lastName);
    List<User> findByFirstNameLikeAndLastNameLike(String firstName, String lastName);
}
