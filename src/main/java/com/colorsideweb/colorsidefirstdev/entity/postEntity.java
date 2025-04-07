package com.colorsideweb.colorsidefirstdev.entity;

import com.colorsideweb.colorsidefirstdev.enums.ColorSchemes;
import com.colorsideweb.colorsidefirstdev.enums.ColorType;
import com.colorsideweb.colorsidefirstdev.enums.ImpressionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class postEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_generator")
    @SequenceGenerator(name = "post_generator", sequenceName = "post_seq", allocationSize = 1)
    @Column(updatable = false, nullable = false)
    private int postId;
    @Column(unique = true, length = 50)
    private String postName;

    private String postNameKey;
    private String description;
    @Column(name = "desc_key")
    private String descKey;
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Date postDate;
    private String colorsCode;
    @Min(2)
    @Max(10)
    private int colorSize;
    private String colorsName;
    private String colorsNameKey;
    private String colorSort;

    private String colorType;

    @Enumerated(EnumType.STRING)
    private ColorSchemes colorSchemes;

    private String impressionType;
    private int userId;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private userEntity userEntity;

    @JsonManagedReference
    @OneToMany(mappedBy = "postEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<likeEntity> likeEntities;

    @JsonManagedReference
    @OneToMany(mappedBy = "postEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<tagEntity> tagEntities;

}
