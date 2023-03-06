package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.devteam.immonexus.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}