package com.colorsideweb.colorsidefirstdev.controller.tag;

import com.colorsideweb.colorsidefirstdev.services.tags.requests.TagRequest;
import com.colorsideweb.colorsidefirstdev.services.tags.services.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagsService service;

//    @DeleteMapping("/delete-tag")
//    public boolean deleteTag(@RequestParam TagRequest tagRequest){
//        return service.deleteTagsByPostId(tagRequest);
//    }


}
