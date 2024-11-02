package StrayCare.prototype.service;

import StrayCare.prototype.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    boolean addUser(User user);
    boolean findUser(String email, String password);
    void updateUser(User user);
    void deleteUser(User user);
    User getByUserId(int id);

}
