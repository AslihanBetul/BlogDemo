package com.abm.bloggerdemo.repository;

import com.abm.bloggerdemo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<Post> findById(Long id);

    //belirli bir kullanıcının yazılarını listemek:
    List<Post> findAllPostByUserId(Long id);

    //belirli bir kategoriye ait yazıları listelemek:
    List<Post> findAllPostByCategoriesId(Long id);
}
