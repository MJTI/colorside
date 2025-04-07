package com.colorsideweb.colorsidefirstdev.services.posts.services;

import com.colorsideweb.colorsidefirstdev.entity.postEntity;
import com.colorsideweb.colorsidefirstdev.repository.postRepo;
import com.colorsideweb.colorsidefirstdev.services.posts.respones.UserPostsResponse;
import com.colorsideweb.colorsidefirstdev.services.tags.services.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPostsService {

    private final postRepo postRepo;
    private final TagsService tagsService;

    public List<UserPostsResponse> userPosts(int userId){
        List<UserPostsResponse> posts = new ArrayList<>();
        List<postEntity> postEntityList = postRepo.findPostsByUserId(userId);

        for (postEntity post : postEntityList) {
            List<String> tags = tagsService.getTagsOfPost(post);

            posts.add(
                    UserPostsResponse.builder()
                            .postName(post.getColorsName())
                            .description(post.getDescription())
                            .numberOfLikes(post.getLikeEntities().size())
                            .colors(Arrays.stream(post.getColorsCode().split("-")).toList())
                            .tags(tags)
                            .createDate(post.getPostDate())
                            .build()
            );
        }
        return posts;
    }
}
