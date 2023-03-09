package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.MessageForum;

import java.util.List;

public interface IMessageForumService {
    List<MessageForum> getAllMessages();

    MessageForum getMessageById(Long id);

    MessageForum saveMessage(MessageForum message);

    void deleteMessage(Long id);

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
     }*/ String makeFine(String val);
}
