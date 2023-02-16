package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Claim;
import tn.devteam.immonexus.Entities.User;

import java.util.List;

public interface IUserService {
    List<User> retrieveAllUsers();

    User addUser(User e);

    User updateUser(User e);

    User retrieveUser(Long idUser);

    User removeUser(Long idUser);
}
