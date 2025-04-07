package com.colorsideweb.colorsidefirstdev.controller.user;

import com.colorsideweb.colorsidefirstdev.services.posts.respones.PostResponse;
import com.colorsideweb.colorsidefirstdev.services.posts.respones.UserPostsResponse;
import com.colorsideweb.colorsidefirstdev.services.users.requests.EditUserRequest;
import com.colorsideweb.colorsidefirstdev.services.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class userController {

    private final UserService service;

    @GetMapping("/posts")
    public List<UserPostsResponse> userPosts(){
        return service.userPosts(service.getUserEmail());
    }

    @GetMapping("/likes")
    public List<PostResponse> userLikes(){
        return service.userLikes(service.getUserEmail());
    }

    @PostMapping("/edit")
    public ResponseEntity editUser(@RequestBody EditUserRequest editRequest){
        try {
            service.editUser(service.getUserEmail(), editRequest);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
