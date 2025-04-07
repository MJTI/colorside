package com.colorsideweb.colorsidefirstdev.controller.user;

import com.colorsideweb.colorsidefirstdev.services.users.requests.AuthenticationRequest;
import com.colorsideweb.colorsidefirstdev.services.users.respones.UserResponse;
import com.colorsideweb.colorsidefirstdev.services.users.services.AuthenticationService;
import com.colorsideweb.colorsidefirstdev.services.users.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class authController {

    private final AuthenticationService service;


    @PostMapping("/register")
    public ResponseEntity register(
            @RequestBody RegisterRequest request){

        try {
            UserResponse user = service.register(request);
            return ResponseEntity.ok(user);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(
            @RequestBody AuthenticationRequest request){
        try {
            UserResponse user = service.authenticate(request);
            return ResponseEntity.ok().body(user);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
