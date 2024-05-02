package com.abm.bloggerdemo.service;

import com.abm.bloggerdemo.dto.request.CommentSaveRequestDto;
import com.abm.bloggerdemo.dto.response.CommentFindAllResponseDto;
import com.abm.bloggerdemo.dto.response.PostFindAllResponseDto;
import com.abm.bloggerdemo.entity.Categories;
import com.abm.bloggerdemo.entity.Comment;
import com.abm.bloggerdemo.entity.Post;
import com.abm.bloggerdemo.entity.User;
import com.abm.bloggerdemo.mapper.CommentMapper;
import com.abm.bloggerdemo.mapper.PostMapper;
import com.abm.bloggerdemo.repository.CommentRepository;
import com.abm.bloggerdemo.repository.PostRepository;
import com.abm.bloggerdemo.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService extends ServiceManager<Comment,Long> {

    private final CommentRepository commentRepository;

    public CommentService( CommentRepository commentRepository) {
        super(commentRepository);

        this.commentRepository = commentRepository;
    }


 //yorum yapma: Mapper ile user ve post idlerini getiremediğim için mappersız kullandım
    public String commentSaveDto(CommentSaveRequestDto dto) {
        User user = User.builder()
                .id(dto.user_id())

                .build();
        Post post = Post.builder()
                .id(dto.post_id())
                .build();
        Comment comment=Comment.builder()
                .user(user)
                .post(post)
                .content(dto.content())
                .build();
        commentRepository.save(comment);
        return "Kayit Basarili";
    }

    public List<CommentFindAllResponseDto> findCommentDto() {
        List<CommentFindAllResponseDto> commentFindAllResponseDtos = new ArrayList<>();
        findAll().forEach(comment -> {
            commentFindAllResponseDtos.add(CommentMapper.INSTANCE.commentToCommentFindAllResponseDto(comment));
        });
        return commentFindAllResponseDtos;
    }
}
