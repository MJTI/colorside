package com.colorsideweb.colorsidefirstdev.services.users.services;

import com.colorsideweb.colorsidefirstdev.entity.ResetPasswordEntity;
import com.colorsideweb.colorsidefirstdev.exception.InvalidEmailException;
import com.colorsideweb.colorsidefirstdev.exception.InvalidTokenException;
import com.colorsideweb.colorsidefirstdev.repository.ResetPasswordRepo;
import com.colorsideweb.colorsidefirstdev.services.email.EmailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class resetPasswordAuthService {

    private final ResetPasswordRepo resetPasswordRepo;
    private final EmailSendService emailSendService;


    public String generateToken(String email){
        String token = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(5);

        resetPasswordRepo.save(ResetPasswordEntity.builder()
                .token(token)
                .userEmail(email)
                .createdAt(createdAt)
                .expiresAt(expiresAt)
                .build());

        return token;
    }

    public String isTokenValid(String token){
        ResetPasswordEntity RPE = resetPasswordRepo.findByToken(token)
                .orElseThrow(()-> new InvalidTokenException("token is invalid!"));
        if(RPE != null && RPE.getExpiresAt().isAfter(LocalDateTime.now())){
            return RPE.getUserEmail();
        }
        resetPasswordRepo.deleteByToken(token);
        throw new InvalidTokenException("token is invalid!");
    }

    public void sendEmail(String email) {
        try {
            String token = generateToken(email);
            emailSendService.sendEmail(email, "http://localhost:8080/reset-password/" + token);
        }
        catch (Exception e){
            throw new InvalidEmailException(e.getMessage());
        }
    }

}
