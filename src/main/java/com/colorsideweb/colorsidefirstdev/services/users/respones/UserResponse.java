package com.colorsideweb.colorsidefirstdev.services.users.respones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String email;
    private String username;
    private String avatar;
    private String bio;
    private Date createDate;
    private boolean isEnable;
    private boolean isPro;
    private String token;
}
