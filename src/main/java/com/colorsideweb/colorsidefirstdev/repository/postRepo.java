package com.colorsideweb.colorsidefirstdev.repository;

import com.colorsideweb.colorsidefirstdev.entity.postEntity;
import com.colorsideweb.colorsidefirstdev.services.posts.respones.PostResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface postRepo extends JpaRepository<postEntity,Integer> {

    Optional<postEntity> findByColorsName(String color);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM post WHERE color_name = :color", nativeQuery = true)
    void deleteByColorName(String color);

    @Transactional
    @Modifying
    @Query(value = """
            UPDATE post
            SET color_code = :colorCode, description = :desc, desc_key = :descKey, colors_number = :colorsNumber
            WHERE color_name = :colorName
            """, nativeQuery = true)
    void editPost(String colorName, String colorCode, String desc, String descKey, int colorsNumber);

    @Query(value = "SELECT * FROM post WHERE user_id = :userId", nativeQuery = true)
    List<postEntity> findPostsByUserId(int userId);

    @Query(value = """
        SELECT *
        FROM post p
        INNER JOIN person u ON u.id = p.user_id
        """, nativeQuery = true)
    List<postEntity> finaAllPosts();

}
