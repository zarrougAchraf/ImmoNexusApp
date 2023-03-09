package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByFirstnameLikeAndLastNameLike(String firstName,String lastName);
}
