package com.colorsideweb.colorsidefirstdev.controller.post;

import com.colorsideweb.colorsidefirstdev.services.posts.respones.PostResponse;
import com.colorsideweb.colorsidefirstdev.services.posts.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/all/posts")
@RequiredArgsConstructor
public class AllPostsController {

    private final PostService service;

    @GetMapping
    public List<PostResponse> getAllPosts(){
        return service.getAllPosts();
    }
}
