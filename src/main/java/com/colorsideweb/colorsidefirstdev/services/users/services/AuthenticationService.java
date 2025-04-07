package com.colorsideweb.colorsidefirstdev.services.users.services;

import com.colorsideweb.colorsidefirstdev.entity.userEntity;
import com.colorsideweb.colorsidefirstdev.enums.Role;
import com.colorsideweb.colorsidefirstdev.exception.InvalidEmailException;
import com.colorsideweb.colorsidefirstdev.exception.InvalidTokenException;
import com.colorsideweb.colorsidefirstdev.exception.UserExistException;
import com.colorsideweb.colorsidefirstdev.repository.userRepo;
import com.colorsideweb.colorsidefirstdev.security.filters.JwtService;
import com.colorsideweb.colorsidefirstdev.services.users.requests.AuthenticationRequest;
import com.colorsideweb.colorsidefirstdev.services.users.requests.RegisterRequest;
import com.colorsideweb.colorsidefirstdev.services.users.respones.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final userRepo userRepo;
    //private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;


    public UserResponse findUserByToken(String token){
        String userEmail = jwtService.extractUsername(token);
        var user = userRepo.findByEmail(userEmail)
                .orElseThrow(()-> new InvalidTokenException("can't find user by token"));
        return UserResponse.builder()
                .email(user.getEmail())
                .username(user.getName())
                .avatar(user.getAvatar())
                .bio(user.getUserBio())
                .createDate(user.getCreateDate())
                .isEnable(user.isEnable())
                .isPro(user.isPro())
                .token(token)
                .build();
    }

    public UserResponse register(@NonNull RegisterRequest request) {

            var user = userEntity.builder()
                    .email(request.getEmail())
                    .name(request.getUsername())
                    .nameKey(request.getUsername().substring(1))
                    .password(request.getPassword())
                    .role(Role.USER)
                    .isEnable(true)
                    .isPro(false)
                    .build();

            try {
                user = userRepo.save(user);
                String token = jwtService.generateToken(user);
                return findUserByToken(token);
            }
            catch (Exception e){
                throw new UserExistException("user already exist!");
        }
    }


    public UserResponse authenticate(@NonNull AuthenticationRequest request) {
        try {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }
        catch (Exception e){
            throw new InvalidEmailException("email or password is wrong!");
        }
        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user);
        return findUserByToken(token);
    }

    public boolean isEmailExist(String email){
        return userRepo.findByEmail(email).isPresent();
    }

    public void updatePassword(@NonNull AuthenticationRequest user) {
        String email = user.getEmail();
        String password = user.getPassword();
        try {
            userRepo.updatePasswordByEmail(email, password);
        }
        catch (Exception e){
            throw new InvalidEmailException(e.getMessage());
        }
    }

    /*private String encodePassword(String password){
        return encoder.encode(password);
    }*/
}
