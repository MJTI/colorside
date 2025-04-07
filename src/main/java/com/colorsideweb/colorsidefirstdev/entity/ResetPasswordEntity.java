package com.colorsideweb.colorsidefirstdev.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reset_password")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResetPasswordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reset_generator")
    @SequenceGenerator(name = "reset_generator", sequenceName = "reset_seq", allocationSize = 1)
    private int id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private String userEmail;
}
