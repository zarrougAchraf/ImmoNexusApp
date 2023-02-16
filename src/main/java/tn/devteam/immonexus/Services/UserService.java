package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.Interfaces.IUserService;
import tn.devteam.immonexus.Repository.UserRepository;

import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user){
        return userRepository.save(user);
    }
    @Override
    public void removeById(Long userId){
         userRepository.deleteById(userId);
    }
    @Override
     public void removeAll(){
        userRepository.deleteAll();
    }
    @Override
    public List<User> allUsers(){
        return userRepository.findAll();
    }

}
