package com.colorsideweb.colorsidefirstdev.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
@RequiredArgsConstructor
public class JwtAuthenticationManager implements AuthenticationManager {

    private final String JWT_KEY;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var provider = new JwtAuthenticationProvider(JWT_KEY);
        if(provider.supports(authentication.getClass())){
            return provider.authenticate(authentication);
        }
        return authentication;
    }
}