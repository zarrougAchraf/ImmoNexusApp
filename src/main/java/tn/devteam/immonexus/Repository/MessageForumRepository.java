package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.MessageForum;

import java.util.List;

@Repository
public interface MessageForumRepository extends JpaRepository<MessageForum,Long> {
   // List<MessageForum> findBySubjectForumId(Long subjectForumId);
}
