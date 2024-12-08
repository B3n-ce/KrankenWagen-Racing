package szfm.krankenwagenracing.admin_user.repository;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import szfm.krankenwagenracing.admin_user.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail2(@Param("email") String email);


}
