package StrayCare.prototype.controller;

import StrayCare.prototype.DTO.LoginCred;
import StrayCare.prototype.DTO.LoginRes;
import StrayCare.prototype.jwt.JwtUtils;
import StrayCare.prototype.model.User;
import StrayCare.prototype.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    //private SecurityConfiguration secConfig;

    @PostMapping("/add")
    public ResponseEntity<String> adduser(@RequestBody User user){
       
        if(userService.addUser(user)){
            return new ResponseEntity<>("User added", HttpStatus.OK);
        } else{
            return new ResponseEntity<>("error encountered",HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginCred loginCred){
//        boolean validUser = userService.findUser(loginCred.getEmail(), loginCred.getPassword());
//        if(validUser){
//            return new ResponseEntity<>("Valid credentials", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Invalid credentials", HttpStatus.NOT_FOUND);

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCred.getEmail(),loginCred.getPassword()));

        } catch (Exception e){
            System.out.println(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("message","Bad credentials");
            map.put("status",false);
            return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateTokenFromEmail(user);
        String role = user.getRole();

        LoginRes res = new LoginRes(jwtToken,user.getEmail(), role);
        return ResponseEntity.ok(res);

    }

    @PostMapping("/info/{id}")
    public ResponseEntity<User> getByUserId(@PathVariable int id){

        return new ResponseEntity<>(userService.getByUserId(id),HttpStatus.OK);
    }

}
