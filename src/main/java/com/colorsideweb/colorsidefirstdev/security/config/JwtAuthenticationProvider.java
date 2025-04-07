package com.colorsideweb.colorsidefirstdev.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final String JWT_KEY;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthentication auth = (JwtAuthentication) authentication;
        if(JWT_KEY.equals(auth.getJWT_TOKEN())){
            auth.setAuthenticated(true);
            return auth;
        }
        throw new BadCredentialsException("token is incorrect!");
    }

    @Override
    public boolean supports(@NonNull Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication.getClass());
    }
}
