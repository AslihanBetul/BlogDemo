package com.abm.bloggerdemo.mapper;

import com.abm.bloggerdemo.dto.request.UserSaveRequestDto;
import com.abm.bloggerdemo.dto.response.UserFindAllResponseDto;
import com.abm.bloggerdemo.entity.User;
import org.hibernate.metamodel.model.domain.MapPersistentAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userSaveRequestDtoToUser(UserSaveRequestDto userSaveRequestDto);

    UserFindAllResponseDto userToUserFindAllResponseDto(User user);
}
