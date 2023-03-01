package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.devteam.immonexus.Entities.SubjectForum;
import tn.devteam.immonexus.Interfaces.ISubjectForumService;
import tn.devteam.immonexus.Repository.SubjectForumRepository;
import tn.devteam.immonexus.Services.SubjectForumService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/SubjectForum")
public class SubjectForumController {

    @Autowired
    SubjectForumService subForumServ;
    /*
    @PostMapping("/addSubForum")
    public SubjectForum addSubjectForum(@RequestBody SubjectForum sub){
        subForumServ.addSubjectForum(sub);
        return sub;
    }

     */
    @GetMapping("/getAll")
    public List<SubjectForum> getAllSubForum(){
        return subForumServ.getAllSubForum();
    }
    @GetMapping("/get/{id}")
    public SubjectForum getAllSubForumById(@PathVariable(value = "id") Long id){
        Optional<SubjectForum> sub = subForumServ.getByIdSubjectForum(id);
        if (sub.isPresent()) {
            return sub.get();
        } else {
            return null;
        }
    }
    @DeleteMapping("remove/{idSubjectForum}")
    public void deleteSubjectForum(@PathVariable(value = "idSubjectForum") Long idSubjectForum){
        subForumServ.deleteSubjectForum(idSubjectForum);
    }
    @PutMapping("update/{idSubjectForum}")
    public SubjectForum updateSubjectForum(@PathVariable(value = "idSubjectForum") Long idSubjectForum,@RequestBody SubjectForum sub ){
        return subForumServ.updateSubjectForum(idSubjectForum,sub);
    }

    /**
     * upload image using multipartfile
     * @param file
     * @param title
     * @param description
     * @return responce entity
     * @throws IOException
     */
    @PostMapping("/saveSubForum")
    public ResponseEntity<SubjectForum> createItem(@RequestPart("image") MultipartFile file,
                                                   @RequestPart("title") String title,
                                                   @RequestPart("description") String description)
            throws IOException {

        SubjectForum item = new SubjectForum();
        item.setTitle(title);
        item.setDescription(description);
        item.setImage(file.getBytes());

        SubjectForum createdItem = subForumServ.addSubjectForum(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }
}
