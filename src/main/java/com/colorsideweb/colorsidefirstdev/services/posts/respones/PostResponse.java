package com.colorsideweb.colorsidefirstdev.services.posts.respones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {

    private String username;
    private String postName;
    private String description;
    private Date createDate;
    private List<String> colors;
    private List<String> tags;
    private int numberOfLikes;

}
