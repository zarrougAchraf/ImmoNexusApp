package tn.devteam.immonexus.Interfaces;

import org.springframework.web.multipart.MultipartFile;
import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.exception.domain.EmailExistException;
import tn.devteam.immonexus.exception.domain.EmailNotFoundException;
import tn.devteam.immonexus.exception.domain.UserNotFoundException;
import tn.devteam.immonexus.exception.domain.UsernameExistException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface IUserService {

    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User addNewUser(String firstname, String lastname, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException;

    User updateUser(String currentUsername,String newFirstname, String newLastname, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException;

    void deleteUser(long id);

    void resetPassword(String email) throws MessagingException, EmailNotFoundException;

    User updateProfileImage(String username,MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException;
}
