package com.colorsideweb.colorsidefirstdev.repository;

import com.colorsideweb.colorsidefirstdev.entity.likeEntity;
import com.colorsideweb.colorsidefirstdev.entity.postEntity;
import com.colorsideweb.colorsidefirstdev.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface likesRepo extends JpaRepository<likeEntity,Integer> {


    @Query(value = """
select p.color_name
    from
        likes l
    join
        post p
            on p.post_id = l.post_id
    left join
        person u
            on u.id = p.user_id
    where
        l.user_id= :userId
""", nativeQuery = true)
    List<String> findPostsByUserId(int userId);

}
