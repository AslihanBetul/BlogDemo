package com.abm.bloggerdemo.service;

import com.abm.bloggerdemo.dto.request.PostSaveRequestDto;
import com.abm.bloggerdemo.dto.response.PostFindAllResponseDto;
import com.abm.bloggerdemo.dto.response.UserFindAllResponseDto;
import com.abm.bloggerdemo.entity.Categories;
import com.abm.bloggerdemo.entity.Post;
import com.abm.bloggerdemo.entity.User;
import com.abm.bloggerdemo.mapper.PostMapper;
import com.abm.bloggerdemo.mapper.UserMapper;
import com.abm.bloggerdemo.repository.PostRepository;
import com.abm.bloggerdemo.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService extends ServiceManager<Post,Long> {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        super(postRepository);
        this.postRepository = postRepository;
    }


    public List<PostFindAllResponseDto> findPostDto() {
        List<PostFindAllResponseDto> postFindAllResponseDtos = new ArrayList<>();

        findAll().forEach(post -> {
            postFindAllResponseDtos.add(PostMapper.INSTANCE.postToPostFindAllResponseDto(post));
        });
        return postFindAllResponseDtos;
    }

    public String savePost1(PostSaveRequestDto postSaveRequestDto){
        User user = User.builder()
                .id(postSaveRequestDto.user_id())
                .build();
        Categories categories = Categories.builder()
                .id(postSaveRequestDto.categories_id())
                .build();
        Post post = Post.builder()
                .user(user)
                .title(postSaveRequestDto.title())
                .content(postSaveRequestDto.content())
                .categories(categories)
                .build();
        user.setPosts(List.of(post));
        postRepository.save(post);
        return "Kayit Basarili";
    }


         //postu id'si ile bulma
    public PostFindAllResponseDto findPostDtoID(Long id) {
        Optional<Post> byId = postRepository.findById(id);
        PostFindAllResponseDto postFindAllResponseDto = PostMapper.INSTANCE.postToPostFindAllResponseDto(byId.get());
        return postFindAllResponseDto;
    }

     //userı id ile bulma
    public List<PostFindAllResponseDto> findAllPostByUserId(Long userId) {
        return postRepository.findAllPostByUserId(userId).stream().map(PostMapper.INSTANCE::postToPostFindAllResponseDto).collect(Collectors.toList());
    }

    //categoriesı id ile bulma

    public List<PostFindAllResponseDto> findAllPostByCategoriesId(Long categoriesId) {
        return postRepository.findAllPostByCategoriesId(categoriesId).stream().map(PostMapper.INSTANCE::postToPostFindAllResponseDto).collect(Collectors.toList());
    }

}
