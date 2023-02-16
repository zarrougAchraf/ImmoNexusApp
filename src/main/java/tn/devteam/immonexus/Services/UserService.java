package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Claim;
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
    public List<User> retrieveAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User addUser(User e) {
        return userRepository.save(e);
    }

    @Override
    public User updateUser(User e) {
        return userRepository.save(e);
    }

    @Override
    public User retrieveUser(Long idUser) {
        return userRepository.findById(idUser).get();
    }

    @Override
    public User removeUser(Long idUser) {
        userRepository.deleteById(idUser);

        return null;
    }
}
