package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Dto.SubjectForumDto;
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
public class SubjectForumService implements ISubjectForumService {

    @Autowired
    SubjectForumRepository subjectForumRepository;
    @Autowired
    MessageForumRepository messageForumRepository;



    @Override
    public SubjectForum addSubjectForum(SubjectForum subjectForum) {

        SubjectForum sub = new SubjectForum();
        sub.setTitle(subjectForum.getTitle());
        sub.setImage(subjectForum.getImage());
        sub.setDescription(this.makeFine(subjectForum.getDescription()));
        return subjectForumRepository.save(sub);
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
        subjectForumRepository.save(subjectForum);

    }


    public String makeFine(String val){
        List<String> badWords = new ArrayList<>();
        badWords.add("shit");
        badWords.add("merde");
        badWords.add("fuck");

        String[] splited = val.split("\\s+");//split  bel espace
        String newval = "";//where we gonna stock
        for(String word : splited){
            String stars = "";//string for affectings stars
            for(String bad : badWords){
                if(word.toLowerCase().equals(bad.toLowerCase())){//low or uppercase
                    for(int i = 0; i<= word.length()-1; i++){
                        stars += "*";//get the stars
                    }

                    newval += stars + " ";//affect it to newval
                    break;
                }
            }
            if(stars.equals("")){
                newval += word + " ";//concat
            }
        }
        return newval;
    }



    public List<SubjectForumDto> getAllSubjectForumDto(){
        //display all subject forum with their users and reactions type
        List<SubjectForumDto> subjectForumDtos = new ArrayList<>();
        List<SubjectForum> subjectForums = subjectForumRepository.findAll();
        for(SubjectForum subjectForum : subjectForums){
            SubjectForumDto subjectForumDto = new SubjectForumDto();
            subjectForumDto.setId(subjectForum.getIdSubjectForum());
            subjectForumDto.setTitle(subjectForum.getTitle());
            subjectForumDto.setDescription(subjectForum.getDescription());
            subjectForumDto.setImage(subjectForum.getImage());
            subjectForumDto.setNbrLike((long) subjectForum.getReactions().size());
            subjectForumDtos.add(subjectForumDto);
        }
        return subjectForumDtos;

    }

}
