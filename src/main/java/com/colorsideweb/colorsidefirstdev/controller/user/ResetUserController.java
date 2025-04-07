package com.colorsideweb.colorsidefirstdev.controller.user;

import com.colorsideweb.colorsidefirstdev.services.email.EmailRequest;
import com.colorsideweb.colorsidefirstdev.services.users.requests.AuthenticationRequest;
import com.colorsideweb.colorsidefirstdev.services.users.services.AuthenticationService;
import com.colorsideweb.colorsidefirstdev.services.users.services.resetPasswordAuthService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/reset")
@RequiredArgsConstructor
public class ResetUserController {

    private final AuthenticationService service;
    private final resetPasswordAuthService resetService;


    @PostMapping("/reset-password")
    public ResponseEntity<String> sendEmail(@RequestBody @NotNull EmailRequest email){
        if (service.isEmailExist(email.getEmail())){
            resetService.sendEmail(email.getEmail());
            return ResponseEntity.ok("email send successfully...");
        }
        return ResponseEntity.badRequest().body("email is not found!");
    }

    @GetMapping("/reset-password/{token}")
    public ResponseEntity<String> resetPassword(@PathVariable String token){
        try {
            String email = resetService.isTokenValid(token);
            return ResponseEntity.ok(email);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity changePassword(@RequestBody AuthenticationRequest user){
        try {
            service.updatePassword(user);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
