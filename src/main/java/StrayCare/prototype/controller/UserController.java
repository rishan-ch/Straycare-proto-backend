package StrayCare.prototype.controller;

import StrayCare.prototype.DTO.LoginCred;
import StrayCare.prototype.configuration.SecurityConfiguration;
import StrayCare.prototype.model.User;
import StrayCare.prototype.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    private SecurityConfiguration secConfig;

    @PostMapping("/add")
    public ResponseEntity<String> adduser(@RequestBody User user){
       
        if(userService.addUser(user)){
            return new ResponseEntity<>("User added", HttpStatus.OK);
        } else{
            return new ResponseEntity<>("error encountered",HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginCred loginCred){
        boolean validUser = userService.findUser(loginCred.getEmail(), loginCred.getPassword());
        if(validUser){
            return new ResponseEntity<>("Valid credentials", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid credentials", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/info/{id}")
    public ResponseEntity<User> getByUserId(@PathVariable int id){

        return new ResponseEntity<>(userService.getByUserId(id),HttpStatus.OK);
    }

}
