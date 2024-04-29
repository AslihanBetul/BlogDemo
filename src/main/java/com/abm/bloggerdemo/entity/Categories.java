package com.abm.bloggerdemo.entity;

import jakarta.persistence.*;
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
@Table(name = "tbl_category")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 200)
    private String description;

    @OneToMany(mappedBy = "categories",cascade = CascadeType.ALL)
    List<Post> posts;
}

