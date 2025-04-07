package com.colorsideweb.colorsidefirstdev.services.users.services;

import com.colorsideweb.colorsidefirstdev.entity.userEntity;
import com.colorsideweb.colorsidefirstdev.exception.InvalidEmailException;
import com.colorsideweb.colorsidefirstdev.repository.likesRepo;
import com.colorsideweb.colorsidefirstdev.repository.userRepo;
import com.colorsideweb.colorsidefirstdev.services.posts.respones.PostResponse;
import com.colorsideweb.colorsidefirstdev.services.posts.respones.UserPostsResponse;
import com.colorsideweb.colorsidefirstdev.services.posts.services.PostService;
import com.colorsideweb.colorsidefirstdev.services.posts.services.UserPostsService;
import com.colorsideweb.colorsidefirstdev.services.users.requests.EditUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final userRepo userRepo;
    private final UserPostsService postsService;
    private final likesRepo likesRepo;
    private final PostService postService;

    public List<UserPostsResponse> userPosts(String email){
        var userId = userRepo.findIdByEmail(email)
                .orElseThrow();
        return postsService.userPosts(userId);
    }

    public String getUserEmail(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    public List<PostResponse> userLikes(String email) {
        userEntity userId = userRepo.findByEmail(email).orElseThrow();
        List<String> posts = likesRepo.findPostsByUserId(userId.getId());
        List<PostResponse> postsList = new ArrayList<>();

        for (String colorName : posts) {
            postsList.add(postService.getPost(colorName));
        }
        return postsList;
    }

    public void editUser(String email, EditUserRequest userRequest){
        try {
            userRepo.updateUserByEmail(email, userRequest.getAvatar(), userRequest.getBio());
        }
        catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
