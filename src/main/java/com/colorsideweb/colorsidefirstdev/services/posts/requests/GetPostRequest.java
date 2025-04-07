package com.colorsideweb.colorsidefirstdev.services.posts.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPostRequest {
    private String postName;
}
