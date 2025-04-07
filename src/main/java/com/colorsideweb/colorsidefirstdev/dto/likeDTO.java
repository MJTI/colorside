package com.colorsideweb.colorsidefirstdev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class likeDTO {
    private int userId;
    private int postId;
}
