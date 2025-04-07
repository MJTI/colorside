package com.colorsideweb.colorsidefirstdev.controller.like;

import com.colorsideweb.colorsidefirstdev.services.likes.services.LikeService;
import com.colorsideweb.colorsidefirstdev.services.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService service;
    private final UserService userService;

    @GetMapping("/like/{colorName}")
    public ResponseEntity likePost(@PathVariable String colorName){
        try {
            service.increase(userService.getUserEmail(), colorName);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/dislike/{colorName}")
    public ResponseEntity dislikePost(@PathVariable String colorName){
        try {
            service.decrease(userService.getUserEmail(), colorName);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
