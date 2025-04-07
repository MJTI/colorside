package com.colorsideweb.colorsidefirstdev.services.likes.respones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeResponse {

    private String username;
    private int numberOfLikes;
}
