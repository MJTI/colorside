package com.colorsideweb.colorsidefirstdev.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(tagEntity.class)
public class tagEntity implements Serializable {

    @Id
    @Column(length = 20)
    private String tagName;
    private String tagNameKey;

    @Id
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "postId", insertable = false, updatable = false)
    private postEntity postEntity;

}
