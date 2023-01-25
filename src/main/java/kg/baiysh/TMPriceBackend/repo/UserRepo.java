package kg.baiysh.TMPriceBackend.repo;


import kg.baiysh.TMPriceBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);

    boolean existsUserByIdOrUsername(String id, String username);
}
