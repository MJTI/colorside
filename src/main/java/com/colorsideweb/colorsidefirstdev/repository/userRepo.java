package com.colorsideweb.colorsidefirstdev.repository;

import com.colorsideweb.colorsidefirstdev.entity.userEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface userRepo extends JpaRepository<userEntity,Integer> {


    Optional<userEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE person SET password = :password WHERE email = :email", nativeQuery = true)
    void updatePasswordByEmail(String email, String password);

    @Transactional
    @Modifying
    @Query(value = "UPDATE person SET avatar = :avatar, bio = :bio WHERE email = :email", nativeQuery = true)
    void updateUserByEmail(String email, String avatar, String bio);

    @Query(value = "SELECT id FROM person WHERE email = :email", nativeQuery = true)
    Optional<Integer> findIdByEmail(String email);

    @Query(value = "SELECT name FROM person WHERE id = :id", nativeQuery = true)
    Optional<String> findNameById(int id);
}
