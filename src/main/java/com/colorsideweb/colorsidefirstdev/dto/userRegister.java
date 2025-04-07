package com.colorsideweb.colorsidefirstdev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class userRegister {
    private String email; // unique.
    private String username; // unique.
    private String password;
}
