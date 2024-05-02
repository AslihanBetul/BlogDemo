package com.abm.bloggerdemo.repository;

import com.abm.bloggerdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

  //name surname ile kullanıcı arama:
    User findByNameIgnoreCaseAndLastnameIgnoreCase(String name, String lastname);

    //ID ile kullanıcı arama:
    Optional<User> findById(Long id);


    //beğenilen posttan beğeniyi geri çekme:


  @Query(value = "DELETE FROM tbl_user_likes WHERE users_id = :x AND likes_id = :y", nativeQuery = true)
  void userUnlikePost(@Param("x") Long userId, @Param("y") Long postId);


}
