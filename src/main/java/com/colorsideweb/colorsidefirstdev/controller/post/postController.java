package com.colorsideweb.colorsidefirstdev.controller.post;

import com.colorsideweb.colorsidefirstdev.services.posts.requests.GetPostRequest;
import com.colorsideweb.colorsidefirstdev.services.posts.requests.PostRequest;
import com.colorsideweb.colorsidefirstdev.services.posts.respones.PostResponse;
import com.colorsideweb.colorsidefirstdev.services.posts.services.PostService;
import com.colorsideweb.colorsidefirstdev.services.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class postController {

    private final PostService service;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody PostRequest post){
        try {
            service.addPost(post, userService.getUserEmail());
            return ResponseEntity.ok().body("post added successfully");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity getPost(@RequestBody GetPostRequest postRequest) {
        try {
            PostResponse post = service.getPost(postRequest.getPostName());
            return ResponseEntity.ok(post);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{postName}")
    public void deletePost(@RequestParam String postName) {
        service.deletePost(postName);
    }

    @PostMapping("/edit")
    public ResponseEntity<String> editPost(@RequestBody PostRequest postRequest){
        try {
            service.editPost(postRequest);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}