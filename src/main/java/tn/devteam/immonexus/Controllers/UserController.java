package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.Interfaces.IUserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/User")
public class UserController {
    IUserService iUserService;


   @PostMapping("/addUser")
   public User addUser(@RequestBody User user){
       return  iUserService.addUser(user);
   }

   @DeleteMapping("/deleteById/{idU}")
    public void removeById(@PathVariable("idU")Long userId){
       iUserService.removeById(userId);
    }
    @DeleteMapping("/deleteAll")
    public void removeAll(){
        iUserService.removeAll();
    }
    @GetMapping("/All-Users")
    public List<User> GetallUsers(){
      return iUserService.allUsers();
    }
}
