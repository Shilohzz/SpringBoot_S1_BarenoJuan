package com.s1.GESTION_PROFESION.Auth;

import com.s1.GESTION_PROFESION.Config.JwtService;
import com.s1.GESTION_PROFESION.Exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        if(request.username().equals("admin") && request.password().equals("1234")) {
            String token = jwtService.generateToken(request.username());
            return ResponseEntity.ok(new LoginResponse(token));
        }
        throw new BusinessRuleException("Credenciales Invalidas");
    }
}
