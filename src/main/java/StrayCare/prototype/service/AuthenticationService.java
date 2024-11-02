package StrayCare.prototype.service;

import StrayCare.prototype.DTO.LoginCred;
import StrayCare.prototype.DTO.LoginRes;
import StrayCare.prototype.DTO.RegisterRequest;
import StrayCare.prototype.configuration.JwtService;
import StrayCare.prototype.model.User;
import StrayCare.prototype.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginRes register(RegisterRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        boolean added = userService.addUser(user);
        String token = jwtService.generateToken(user);

        return LoginRes
                .builder()
                .jwtToken(jwtService.generateToken(user))
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .build();
    }

    public LoginRes authenticate(LoginCred loginCred) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCred.getEmail(), loginCred.getPassword()));
        User user = (User) userService.loadUserByUsername(loginCred.getEmail());

        String token = jwtService.generateToken(user);
        return LoginRes
                .builder()
                .jwtToken(jwtService.generateToken(user))
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .build();
    }
}
