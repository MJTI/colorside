package com.colorsideweb.colorsidefirstdev.services.users.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditUserRequest {
    private String avatar;
    private String bio;
}
