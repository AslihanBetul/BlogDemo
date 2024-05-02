package com.abm.bloggerdemo.mapper;

import com.abm.bloggerdemo.dto.request.CommentSaveRequestDto;
import com.abm.bloggerdemo.dto.response.CommentFindAllResponseDto;
import com.abm.bloggerdemo.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);


    @Mapping(target = "user_id", source = "comment.user.id")
    @Mapping(target = "post_id", source = "comment.post.id")
    CommentFindAllResponseDto commentToCommentFindAllResponseDto (Comment comment);

    Comment commentSaveRequestDtoToComment(CommentSaveRequestDto commentSaveRequestDto);






}
