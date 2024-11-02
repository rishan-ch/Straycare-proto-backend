package StrayCare.prototype.controller;

import StrayCare.prototype.DTO.LoginCred;
import StrayCare.prototype.DTO.LoginRes;
import StrayCare.prototype.DTO.RegisterRequest;
import StrayCare.prototype.model.User;
import StrayCare.prototype.service.AuthenticationService;
import StrayCare.prototype.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    private final AuthenticationService authenticationService;


    @PostMapping("/add")
    public ResponseEntity<LoginRes> adduser(@RequestBody RegisterRequest request){

        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginCred loginCred){
        return new ResponseEntity<>(authenticationService.authenticate(loginCred), HttpStatus.OK);
//        boolean validUser = userService.findUser(loginCred.getEmail(), loginCred.getPassword());
//        if(validUser){
//            return new ResponseEntity<>("Valid credentials", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Invalid credentials", HttpStatus.NOT_FOUND);


    }

    @PostMapping("/info/{id}")
    public ResponseEntity<User> getByUserId(@PathVariable int id){

        return new ResponseEntity<>(userService.getByUserId(id),HttpStatus.OK);
    }

}
