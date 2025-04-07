package com.colorsideweb.colorsidefirstdev.entity;

import com.colorsideweb.colorsidefirstdev.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class userEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(unique = true, nullable = false, length = 50)
    private String email;
    @Column(unique = true, nullable = false, length = 50)
    private String name;

    private String nameKey;
    @Column(nullable = false)
    private String password;
    private String avatar;
    @Column(name = "bio")
    private String userBio;
    @CreationTimestamp
    @Column(name = "create_date", updatable = false, nullable = false)
    private Date createDate;
    @Column(name = "is_pro")
    private boolean isPro;
    @Column(name = "is_enable")
    private boolean isEnable;
    @Enumerated(EnumType.STRING)
    private Role role;
    @JsonManagedReference
    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<postEntity> postEntities;

    @JsonManagedReference
    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<likeEntity> likeEntities;

    public userEntity(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isEnable;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }
}
