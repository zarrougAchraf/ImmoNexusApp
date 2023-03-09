package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.SubjectForum;

import java.util.List;
import java.util.Optional;

public interface ISubjectForumService {
    SubjectForum addSubjectForum(SubjectForum subjectForum);
    List<SubjectForum> getAllSubForum();
    Optional<SubjectForum> getByIdSubjectForum(Long idSubjectForum);
    void deleteSubjectForum(Long idSubjectForum);
    SubjectForum updateSubjectForum(Long idSubjectForum, SubjectForum subjectForum);

}
