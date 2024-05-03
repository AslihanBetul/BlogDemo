package com.abm.bloggerdemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 25)
    private String name;
    @Column(length = 25)
    private String lastname;
    @Email(message = "Can only use gmail")
    private String email;
    @Column(length = 10, nullable = false)
    @Size(min = 1, max = 10, message = "Password must be 8 characters long")
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Post> posts;

    @ManyToMany
    List<Post> likes;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Comment> comments;

}