package StrayCare.prototype.service;

import StrayCare.prototype.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean addUser(User user);
    boolean findUser(String email, String password);
    void updateUser(User user);
    void deleteUser(User user);
    User getByUserId(int id);
}
