package com.colorsideweb.colorsidefirstdev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetUserRequest {
    private String token;
    private String newPassword;
}
