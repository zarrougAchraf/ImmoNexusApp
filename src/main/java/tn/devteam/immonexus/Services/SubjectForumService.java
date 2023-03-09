package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.MessageForum;
import tn.devteam.immonexus.Entities.SubjectForum;
import tn.devteam.immonexus.Interfaces.ISubjectForumService;
import tn.devteam.immonexus.Repository.MessageForumRepository;
import tn.devteam.immonexus.Repository.SubjectForumRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SubjectForumService implements ISubjectForumService{
    @Autowired
    SubjectForumRepository subjectForumRepository;
    @Autowired
    MessageForumRepository messageForumRepository;



    @Override
    public SubjectForum addSubjectForum(SubjectForum subjectForum) {

        return subjectForumRepository.save(subjectForum);
    }

    @Override
    public List<SubjectForum> getAllSubForum() {
        List<SubjectForum> all = new ArrayList<>();
        all.addAll(subjectForumRepository.findAll());
        System.out.println("*****************  TEST   ************************* ");
        System.out.println(all);
        return subjectForumRepository.findAll();
    }

    @Override
    public Optional<SubjectForum> getByIdSubjectForum(Long idSubjectForum) {
        return subjectForumRepository.findById(idSubjectForum);
    }

    @Override
    public void deleteSubjectForum(Long idSubjectForum) {
        subjectForumRepository.deleteById(idSubjectForum);
    }

    @Override
    public SubjectForum updateSubjectForum(Long idSubjectForum, SubjectForum subjectForum) {
            SubjectForum sub = new SubjectForum();
            sub=subjectForumRepository.findById(idSubjectForum).get();
            if(sub!=null){
                sub.setTitle(subjectForum.getTitle());
                sub.setDescription(subjectForum.getDescription());
                sub.setImage(subjectForum.getImage());
                subjectForumRepository.save(sub);
                        return  sub;
            }

                    return null;
    }
/*
    public SubjectForum updateForum(Long idSubjectForum, SubjectForum forum) {
        SubjectForum existingForum = subjectForumRepository.findById(idSubjectForum)
                .orElseThrow(() -> new RuntimeException("Forum not found with id " + idSubjectForum));

        existingForum.setTitle(forum.getTitle());
        existingForum.setDescription(forum.getDescription());
        existingForum.setImage(forum.getImage());

        return subjectForumRepository.save(existingForum);
    }
*/

    public void assignCommentsToPost(Long idSubjectForum, Long idMsgForum) {
        SubjectForum subjectForum = subjectForumRepository.findById(idSubjectForum).orElse(null);
        MessageForum messageForum = messageForumRepository.findById(idMsgForum).orElse(null);
        subjectForum.getComments().add(messageForum);
        subjectForumRepository.save(subjectForum);

    }
}
