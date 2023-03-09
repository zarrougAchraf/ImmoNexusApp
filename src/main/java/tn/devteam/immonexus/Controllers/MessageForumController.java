package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.MessageForum;
import tn.devteam.immonexus.Interfaces.IMessageForumService;
import tn.devteam.immonexus.Services.MessageForumService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/MEssageForum")
public class MessageForumController {
    @Autowired
    private MessageForumService messageForumService;

    @GetMapping("")
    public List<MessageForum> getAllMessages() {

        return messageForumService.getAllMessages();
    }

    @GetMapping("/{id}")
    public MessageForum getMessageById(@PathVariable Long id) {
        return messageForumService.getMessageById(id);
    }

    @PostMapping("/add")
    public MessageForum saveMessage(@Valid @RequestBody MessageForum message) {
        return messageForumService.saveMessage(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageForumService.deleteMessage(id);
        return ResponseEntity.ok().build();
    }

    /*
    @GetMapping("/subject/{subjectId}")
    public List<MessageForum> getMessagesBySubjectForumId(@PathVariable Long subjectId) {
        return messageForumService.getMessagesBySubjectForumId(subjectId);
    }
*/
/*@PostMapping("/{opperation}/{id_post}/{id_user}/likes")
public MessageForum updateLikes(@PathVariable Long id_post,@PathVariable Long id_user,@PathVariable String opperation) {
    return messageForumService.updateLikes(id_post,id_user,opperation);
}

@PostMapping("/{opperation}/{id_post}/{id_user}/dislikes")
public MessageForum updateDislikes(@PathVariable Long id_post,@PathVariable Long id_user,@PathVariable String opperation) {
    return messageForumService.updateDislikes(id_post,id_user,opperation);
}
*/}
