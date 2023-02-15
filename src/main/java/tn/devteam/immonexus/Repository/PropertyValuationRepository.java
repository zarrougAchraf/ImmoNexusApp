package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Properties;
@Repository
public interface PropertyValuationRepository extends JpaRepository<Long, Properties> {
}
