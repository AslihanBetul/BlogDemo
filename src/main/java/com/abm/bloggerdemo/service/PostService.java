package com.abm.bloggerdemo.service;

import com.abm.bloggerdemo.dto.request.PostSaveRequestDto;
import com.abm.bloggerdemo.dto.response.CategoryFindAllResponseDto;
import com.abm.bloggerdemo.dto.response.PostFindAllResponseDto;
import com.abm.bloggerdemo.dto.response.UserFindAllResponseDto;
import com.abm.bloggerdemo.entity.Categories;
import com.abm.bloggerdemo.entity.Post;
import com.abm.bloggerdemo.entity.User;
import com.abm.bloggerdemo.exception.BloggerDemoAppException;
import com.abm.bloggerdemo.exception.ErrorType;
import com.abm.bloggerdemo.mapper.PostMapper;
import com.abm.bloggerdemo.mapper.UserMapper;
import com.abm.bloggerdemo.repository.PostRepository;
import com.abm.bloggerdemo.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class PostService extends ServiceManager<Post,Long> {
    private final PostRepository postRepository;
    private final UserService userService;
    private final CategoriesService categoriesService;

    public PostService(PostRepository postRepository, UserService userService, CategoriesService categoriesService) {
        super(postRepository);
        this.postRepository = postRepository;
        this.userService = userService;
        this.categoriesService = categoriesService;
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
        if (byId.isEmpty()){
            throw new BloggerDemoAppException(ErrorType.POST_NOT_FOUND,"Post not found");
        }
        PostFindAllResponseDto postFindAllResponseDto = PostMapper.INSTANCE.postToPostFindAllResponseDto(byId.get());
        return postFindAllResponseDto;
    }

     //userı id ile bulma
    public List<PostFindAllResponseDto> findAllPostByUserId(Long userId) {
        Optional<User> byId = userService.findById(userId);
        if (byId.isEmpty()){
            throw new BloggerDemoAppException(ErrorType.USER_NOT_FOUND,"User not found");
        }
        return postRepository.findAllPostByUserId(userId).stream().map(PostMapper.INSTANCE::postToPostFindAllResponseDto).collect(Collectors.toList());
    }

    //categoriesı id ile bulma

    public List<PostFindAllResponseDto> findAllPostByCategoriesId(Long categoriesId) {
        Optional<Categories> byId = categoriesService.findById(categoriesId);
        if (byId.isEmpty()){
            throw new BloggerDemoAppException(ErrorType.CATEGORY_NOT_FOUND,"Categories not found");
        }
        return postRepository.findAllPostByCategoriesId(categoriesId).stream().map(PostMapper.INSTANCE::postToPostFindAllResponseDto).collect(Collectors.toList());
    }

    public String updatePost(Long id,String title,String content){
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isEmpty()){
            throw new BloggerDemoAppException(ErrorType.POST_NOT_FOUND,"Post not found");
        }
        Post post = byId.get();
        post.setTitle(title);
        post.setContent(content);
        post.setPublished_at(LocalDate.now());
        postRepository.save(post);
        return "Guncelleme Basarili";
    }

    public String deletePost(Long id){
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isEmpty()){
            throw new BloggerDemoAppException(ErrorType.POST_NOT_FOUND,"Post not found");
        }
        Post post = byId.get();
        postRepository.delete(post);
        return "Silme Basarili";
    }

    public List<PostFindAllResponseDto> findAllPostByContentContaining(String contentContaining) {
        List<PostFindAllResponseDto> postFindAllResponseDtos = new ArrayList<>();
        List<Post> ListPost = postRepository.findAllPostByContentContaining(contentContaining);
        if(ListPost.isEmpty()){
            throw new BloggerDemoAppException(ErrorType.POST_NOT_FOUND, "Post not found");
        }

        postRepository.findAllPostByContentContaining(contentContaining).forEach(post -> {
            postFindAllResponseDtos.add(PostMapper.INSTANCE.postToPostFindAllResponseDto(post));
        });
        return postFindAllResponseDtos;
    }



    //kategorinin ismine göre post getirme :
    public List<PostFindAllResponseDto> findAllPostByCategoriesNameContainingIgnoreCase(String name) {
        List<PostFindAllResponseDto> postFindAllResponseDtos = new ArrayList<>();
        List<Post> allPostByCategoriesNameContainingIgnoreCase = postRepository.findAllPostByCategoriesNameContainingIgnoreCase(name);
        if(allPostByCategoriesNameContainingIgnoreCase.isEmpty()){
            throw new BloggerDemoAppException(ErrorType.CATEGORY_NOT_FOUND, "Post not found");
        }

        postRepository.findAllPostByCategoriesNameContainingIgnoreCase(name).forEach(post -> {
            postFindAllResponseDtos.add(PostMapper.INSTANCE.postToPostFindAllResponseDto(post));
        });
        return postFindAllResponseDtos;
    }


    //yazıları yayın tarihine göre sıralama:
    public List<PostFindAllResponseDto> FindAllPostOrderByPublished_at() {
        List<PostFindAllResponseDto> postFindAllResponseDtos = new ArrayList<>();

        postRepository. findAllPublished().forEach(post -> {
            postFindAllResponseDtos.add(PostMapper.INSTANCE.postToPostFindAllResponseDto(post));
        });
        return postFindAllResponseDtos;
    }

}
