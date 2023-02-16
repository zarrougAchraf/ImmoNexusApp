package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.User;

import java.util.List;

public interface IUserService {
    void removeById(Long userId);

    User addUser(User user);

    void removeAll();

    List<User> allUsers();
}
