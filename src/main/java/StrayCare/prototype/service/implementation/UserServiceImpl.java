package StrayCare.prototype.service.implementation;

import StrayCare.prototype.model.User;
import StrayCare.prototype.repository.UserRepository;
import StrayCare.prototype.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo;

    @Override
    public boolean addUser(User user) {
        try{
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean findUser(String email, String password) {
        User user = userRepo.findByEmailAndPassword(email, password);
        if(user!=null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public User getByUserId(int id) {
        return userRepo.findByUserId(id);
    }
}
