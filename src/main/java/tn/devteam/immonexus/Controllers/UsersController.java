package tn.devteam.immonexus.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.Repository.UserRepository;
import tn.devteam.immonexus.storage.UserStorage;

import java.util.Set;

@RestController
@CrossOrigin
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration/{userName}")
    public User register(@PathVariable User user) {
     return  userRepository.save(user);
    }

    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll() {
        return UserStorage.getInstance().getUsers();
    }
}
