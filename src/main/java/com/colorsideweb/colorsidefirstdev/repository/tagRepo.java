package com.colorsideweb.colorsidefirstdev.repository;

import com.colorsideweb.colorsidefirstdev.entity.tagEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface tagRepo extends JpaRepository<tagEntity,Integer> {


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tags WHERE post_id = :postId", nativeQuery = true)
    void deleteTagsByPostId(int postId);



}
