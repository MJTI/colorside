package com.colorsideweb.colorsidefirstdev.services.posts.services;

import com.colorsideweb.colorsidefirstdev.entity.postEntity;
import com.colorsideweb.colorsidefirstdev.exception.InvalidPostException;
import com.colorsideweb.colorsidefirstdev.exception.PostExistException;
import com.colorsideweb.colorsidefirstdev.repository.postRepo;
import com.colorsideweb.colorsidefirstdev.repository.userRepo;
import com.colorsideweb.colorsidefirstdev.services.posts.requests.PostRequest;
import com.colorsideweb.colorsidefirstdev.services.posts.respones.PostResponse;
import com.colorsideweb.colorsidefirstdev.services.tags.services.TagsService;

import lombok.RequiredArgsConstructor;

import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final postRepo postRepo;
    private final userRepo userRepo;
    private final TagsService tagsService;


    public void addPost(@NonNull PostRequest post, String email) {

        int userId = userRepo.findIdByEmail(email).orElseThrow();

            var postEnt = postEntity.builder()
                    .userId(userId)
                    .postName(post.getColorName())
                    .postNameKey(post.getColorName().substring(2))
                    .colorsCode(String.join("-", post.getColors()))
                    .description(post.getDescription())
                    .descKey(post.getDescription().substring(2))
                    .colorSize(post.getColors().size())
                    .build();
            try {
                postEntity p = postRepo.save(postEnt);
                tagsService.addTag(post.getTags(), p);
            }
            catch (Exception e){
                throw new PostExistException("post already exist!");
            }
    }

    public PostResponse getPost(String colorName){
        postEntity post = postRepo.findByColorsName(colorName).orElse(null);
        if(post == null) throw new UsernameNotFoundException("post not found!");

        String username = userRepo.findNameById(post.getUserId()).orElse(null);
        List<String> tags = tagsService.getTagsOfPost(post);

        return PostResponse.builder()
                .username(username)
                .postName(post.getPostName())
                .description(post.getDescription())
                .createDate(post.getPostDate())
                .colors(Arrays.stream(post.getColorsCode().split("-")).toList())
                .tags(tags)
                .numberOfLikes(post.getLikeEntities().size())
                .build();
    }

    public void deletePost(String colorName) {
        postRepo.deleteByColorName(colorName);
    }

    public void editPost(@NonNull PostRequest postRequest){
        try {
            postEntity post = postRepo.findByColorsName(postRequest.getColorName()).orElseThrow();
            postRepo.editPost(
                    postRequest.getColorName(),
                    String.join("-", postRequest.getColors()),
                    postRequest.getDescription(),
                    postRequest.getDescription().substring(2),
                    postRequest.getColors().size()
            );
            tagsService.deleteTagsByPostId(post.getPostId());
            tagsService.addTag(postRequest.getTags(), post);
        }
        catch (Exception e){
            throw new InvalidPostException("post not found!");
        }
    }

    public List<PostResponse> getAllPosts() {
        List<postEntity> p = postRepo.finaAllPosts();
        List<PostResponse> postList = new ArrayList<>();

        for (postEntity post : p) {
            postList.add(
                    PostResponse.builder()
                            .username(userRepo.findNameById(post.getUserId()).orElse(null))
                            .postName(post.getPostName())
                            .description(post.getDescription())
                            .colors(Arrays.stream(post.getColorsCode().split("-")).toList())
                            .tags(tagsService.getTagsOfPost(post))
                            .createDate(post.getPostDate())
                            .numberOfLikes(post.getLikeEntities().size())
                            .build()
            );
        }
        return postList;
    }
}
