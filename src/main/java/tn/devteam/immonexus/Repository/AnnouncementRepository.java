package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.devteam.immonexus.Entities.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}