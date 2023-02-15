package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Interfaces.IUserService;
import tn.devteam.immonexus.Repository.UserRepository;

@Service
@Slf4j
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
}
