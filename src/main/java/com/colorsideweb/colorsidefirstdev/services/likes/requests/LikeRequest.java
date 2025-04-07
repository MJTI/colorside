package com.colorsideweb.colorsidefirstdev.services.likes.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeRequest {
    private String username;
    private String colorName;
}
