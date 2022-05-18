package site.metacoding.api_address.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    // null일 수도 있기 때문에 Optional에 담아준다.
    @Query(value = "SELECT * FROM user WHERE username=:username", nativeQuery = true)
    Optional<User> FindByUsername(@Param("username") String username);
}
