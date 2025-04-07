package com.colorsideweb.colorsidefirstdev.services.tags.services;

import com.colorsideweb.colorsidefirstdev.entity.postEntity;
import com.colorsideweb.colorsidefirstdev.entity.tagEntity;
import com.colorsideweb.colorsidefirstdev.repository.tagRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagsService {

    private final tagRepo tagRepo;


    public void addTag(@NonNull List<String> tags,@NonNull postEntity post){

        List<tagEntity> tagList = new ArrayList<>();
        for (String tag : tags) {
            tagList.add(tagEntity.builder()
                    .postEntity(post)
                    .tagName(tag)
                    .tagNameKey(tag.substring(2))
                    .build());
        }
        tagRepo.saveAll(tagList);
    }

    public void deleteTagsByPostId(int postId){
        tagRepo.deleteTagsByPostId(postId);
    }

    public List<String> getTagsOfPost(@NonNull postEntity post){
        List<String> tags = new ArrayList<>();
        for (tagEntity tag : post.getTagEntities()) {
            tags.add(tag.getTagName());
        }
        return tags;
    }
}
