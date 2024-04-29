package com.abm.bloggerdemo.mapper;

import com.abm.bloggerdemo.dto.request.PostSaveRequestDto;
import com.abm.bloggerdemo.dto.response.PostFindAllResponseDto;
import com.abm.bloggerdemo.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "user_id", source = "post.user.id")
    @Mapping(target = "categories_id", source = "post.categories.id")
    PostFindAllResponseDto postToPostFindAllResponseDto (Post post);
    Post postSaveRequestDtoToPost(PostSaveRequestDto dto);

}
