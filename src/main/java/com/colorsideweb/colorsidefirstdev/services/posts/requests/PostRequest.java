package com.colorsideweb.colorsidefirstdev.services.posts.requests;

import com.colorsideweb.colorsidefirstdev.repository.postRepo;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequest {
    private String colorName;
    private String description;
    private List<String> tags;
    private List<String> colors;

}
