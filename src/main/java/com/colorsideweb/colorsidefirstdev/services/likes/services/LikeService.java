package com.colorsideweb.colorsidefirstdev.services.likes.services;

import com.colorsideweb.colorsidefirstdev.entity.likeEntity;
import com.colorsideweb.colorsidefirstdev.entity.postEntity;
import com.colorsideweb.colorsidefirstdev.entity.userEntity;
import com.colorsideweb.colorsidefirstdev.exception.InvalidEmailException;
import com.colorsideweb.colorsidefirstdev.exception.InvalidPostException;
import com.colorsideweb.colorsidefirstdev.repository.likesRepo;
import com.colorsideweb.colorsidefirstdev.repository.postRepo;
import com.colorsideweb.colorsidefirstdev.repository.userRepo;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final postRepo postRepo;
    private final userRepo userRepo;
    private final likesRepo likesRepo;

    private likeEntity getLike(String email, String colorName){
        userEntity user = userRepo.findByEmail(email)
                .orElseThrow(()-> new InvalidEmailException("username not found!"));
        postEntity post = postRepo.findByColorsName(colorName)
                .orElseThrow(()-> new InvalidPostException("post not found!"));

        return likeEntity.builder()
                .userEntity(user)
                .postEntity(post)
                .build();
    }

    public void increase(String email, String colorName){
        try {
            likeEntity likes = getLike(email, colorName);
            likesRepo.save(likes);
        }
        catch (Exception e){
            throw new EntityExistsException(e.getMessage());
        }
    }

    public void decrease(String email, String colorName) {
        try {
            likeEntity likes = getLike(email, colorName);
            likesRepo.delete(likes);
        }
        catch (Exception e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }

}
