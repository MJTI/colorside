package com.colorsideweb.colorsidefirstdev.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "likes")
@IdClass(likeEntity.class)
@Builder
public class likeEntity implements Serializable {

    @Id
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private userEntity userEntity;

    @Id
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "postId", insertable = false, updatable = false)
    private postEntity postEntity;
}
