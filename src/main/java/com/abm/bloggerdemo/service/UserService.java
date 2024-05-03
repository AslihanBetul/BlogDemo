package com.abm.bloggerdemo.service;

import com.abm.bloggerdemo.dto.request.UserSaveRequestDto;
import com.abm.bloggerdemo.dto.response.UserFindAllResponseDto;
import com.abm.bloggerdemo.entity.Post;
import com.abm.bloggerdemo.entity.User;
import com.abm.bloggerdemo.exception.BloggerDemoAppException;
import com.abm.bloggerdemo.exception.ErrorType;
import com.abm.bloggerdemo.mapper.UserMapper;
import com.abm.bloggerdemo.repository.CategoriesRepository;
import com.abm.bloggerdemo.repository.PostRepository;
import com.abm.bloggerdemo.repository.UserRepository;
import com.abm.bloggerdemo.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService extends ServiceManager<User,Long> {
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public UserService(UserRepository userRepository, PostRepository postRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.postRepository = postRepository;

    }

    public void userSaveDto(UserSaveRequestDto dto) {
        userRepository.save(UserMapper.INSTANCE.userSaveRequestDtoToUser(dto));
    }

    public List<UserFindAllResponseDto> findUserDto() {
        List<UserFindAllResponseDto> userFindAllResponseDtos = new ArrayList<>();

        findAll().forEach(user -> {
            userFindAllResponseDtos.add(UserMapper.INSTANCE.userToUserFindAllResponseDto(user));
        });
        return userFindAllResponseDtos;
    }

    public UserFindAllResponseDto findUserDto2(String name, String lastname) {

        User byNameAndByLastname = userRepository.findByNameIgnoreCaseAndLastnameIgnoreCase(name, lastname);
        UserFindAllResponseDto userFindAllResponseDto = UserMapper.INSTANCE.userToUserFindAllResponseDto(byNameAndByLastname);

        return userFindAllResponseDto;
    }

    //belirli bir kullanıcının id'sine göre detaylarını getiren metod:
    public UserFindAllResponseDto findUserDtoID(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new BloggerDemoAppException(ErrorType.USER_NOT_FOUND, "User not found");
        }
        UserFindAllResponseDto userFindAllResponseDto = UserMapper.INSTANCE.userToUserFindAllResponseDto(byId.get());
        return userFindAllResponseDto;


    }

    //user güncelleme:
    public String updateUser(Long id, String name, String lastName, String email, String password) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new BloggerDemoAppException(ErrorType.USER_NOT_FOUND, "User not found");
        }
        User user = byId.get();
        user.setName(name);
        user.setLastname(lastName);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return "Güncelleme Basarili";
    }

    //user silme:
    public String deleteUser(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new BloggerDemoAppException(ErrorType.USER_NOT_FOUND, "User not found");
        }
        User user = byId.get();
        userRepository.delete(user);
        return "Kullanıcı silme işlemi  basarili";
    }

    //post like etme:
    public void userLikePost(Long userId, Long postId) {
        Optional<User> user1 = userRepository.findById(userId);
        Optional<Post> post1 = postRepository.findById(postId);

        if (user1.isPresent() && post1.isPresent()) {
            User user = user1.get();
            Post post = post1.get();


            if (!user.getLikes().contains(post)) {
                user.getLikes().add(post);
                userRepository.save(user);
            }


            if (!post.getUsers().contains(user)) {
                post.getUsers().add(user);
                postRepository.save(post);
            }
        } else {
            throw new BloggerDemoAppException(ErrorType.USER_OR_POST_NOT_FOUND, "User or Post not found");
        }


    }


    //post unlike etme:
    public String userLikePostDelete(Long userId, Long postId) {

        Optional<User> user1 = userRepository.findById(userId);
        Optional<Post> post1 = postRepository.findById(postId);


        if (user1.isPresent() && post1.isPresent()) {
            User user = user1.get();
            Post post = post1.get();


            post.getUsers().remove(user);
            postRepository.save(post);


            user.getLikes().remove(post);
            userRepository.save(user);

            return "Silme Başarılı";

        } else {
            throw new BloggerDemoAppException(ErrorType.USER_OR_POST_NOT_FOUND, "User or Post not found");
        }

    }


}






