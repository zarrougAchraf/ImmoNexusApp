package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tn.devteam.immonexus.Entities.MessageForum;
import tn.devteam.immonexus.Entities.SubjectForum;
import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.Interfaces.IMessageForumService;
import tn.devteam.immonexus.Repository.MessageForumRepository;
import tn.devteam.immonexus.Repository.SubjectForumRepository;
import tn.devteam.immonexus.Repository.UserRepository;

import java.util.List;

@Service
@Slf4j
public class MessageForumService implements IMessageForumService {

    @Autowired
    private MessageForumRepository messageForumRepository;
    @Autowired
    UserRepository userRepository;
    

    public List<MessageForum> getAllMessages() {
        return messageForumRepository.findAll();
    }

    public MessageForum getMessageById(Long id) {
        return messageForumRepository.findById(id).orElseThrow(() -> new NotFoundException("Commentaire non trouvé avec l'id " + id));
    }

    public MessageForum saveMessage(MessageForum message) {
        return messageForumRepository.save(message);
    }

    public void deleteMessage(Long id) {
        messageForumRepository.deleteById(id);
    }
    public MessageForum updateLikes(Long id_post,Long id_user,String opperation) {
    MessageForum msg=messageForumRepository.getById(id_post);
        User currentuser=userRepository.getById(id_user);
        List<MessageForum> likedpost=currentuser.getLikes();
        if(!likedpost.contains(msg)){
            if(opperation.equals("+")){
                likedpost.add(msg);
            }
           
        }
        else

        {
           if(opperation.equals("-")){
            likedpost.remove(msg);
           } 
        }
      
       // msg.setLikes(likes);


        return messageForumRepository.save(msg);
    }

    public MessageForum updateDislikes(Long id_post,Long id_user, String opperation) {
        MessageForum msg=messageForumRepository.getById(id_post);
        int Dislikes=msg.getDislikes();
        if(opperation.equals("+")){
            Dislikes++;
        }
        else{
            Dislikes--;
        }
        msg.setDislikes(Dislikes);


        return messageForumRepository.save(msg);
    }

   /* public List<MessageForum> getMessagesBySubjectForumId(Long subjectForumId) {
        return messageForumRepository.findBySubjectForumId(subjectForumId);
    }*/

   /* public MessageForum updateLikes(Long id) {
        MessageForum message = getMessageById(id);
        message.setLikes(message.getLikes() + 1);
        return messageForumRepository.save(message);
    }

    public MessageForum updateDislikes(Long id) {
        MessageForum message = getMessageById(id);
        message.setDislikes(message.getDislikes() + 1);
        return messageForumRepository.save(message);
    }
*/
  /*  @Override
    public MessageForum createMessage(Long id, MessageForum messageForum) {
        SubjectForum subForum = subjectForumService.getByIdSubjectForum(id).get();
        if(subForum == null){
            System.out.println("subject forum does not exist for the id : "+id);
        }
        else {
            messageForum.setSubjectForum(subForum);
            
        }



        return null;
    }*/
}
