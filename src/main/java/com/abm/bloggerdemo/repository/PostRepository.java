package com.abm.bloggerdemo.repository;

import com.abm.bloggerdemo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<Post> findById(Long id);

    //belirli bir kullanıcının yazılarını listemek:
    List<Post> findAllPostByUserId(Long id);

    //belirli bir kategoriye ait yazıları listelemek:
    List<Post> findAllPostByCategoriesId(Long id);

    //belirli bir kelimeye göre post(yazı) getirme
    List<Post> findAllPostByContentContaining(String content);


   // belirli kategorinin ismine göre post getirme :
    List<Post> findAllPostByCategoriesNameContainingIgnoreCase(String categoriesNameContaining);

   //yazıları yayın tarihine göre sıralama:
    @Query("SELECT p FROM Post p ORDER BY p.published_at ")
    List<Post> findAllPublished();



}
