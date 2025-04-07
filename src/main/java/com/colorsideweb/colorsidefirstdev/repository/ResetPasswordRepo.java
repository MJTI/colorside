package com.colorsideweb.colorsidefirstdev.repository;

import com.colorsideweb.colorsidefirstdev.entity.ResetPasswordEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface ResetPasswordRepo extends JpaRepository<ResetPasswordEntity, Integer> {

    Optional<ResetPasswordEntity> findByToken(String token);

    @Transactional
    @Modifying
    void deleteByToken(String token);

}
