package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.exception.domain.EmailExistException;
import tn.devteam.immonexus.exception.domain.UserNotFoundException;
import tn.devteam.immonexus.exception.domain.UsernameExistException;

import java.util.List;

public interface IUserService {

    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
