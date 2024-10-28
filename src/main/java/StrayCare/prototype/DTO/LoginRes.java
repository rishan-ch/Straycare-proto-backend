package StrayCare.prototype.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class LoginRes {
    private String jwtToken;
    private String email;
    private String role;
}
