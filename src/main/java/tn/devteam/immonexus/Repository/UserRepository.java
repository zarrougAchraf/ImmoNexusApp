package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.devteam.immonexus.Entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
}

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsername(String username);

    User findUserByEmail(String email);
}

