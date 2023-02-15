package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.Announcement;
@Repository
public interface AnnouncementRepository extends JpaRepository<Long, Announcement> {
}
