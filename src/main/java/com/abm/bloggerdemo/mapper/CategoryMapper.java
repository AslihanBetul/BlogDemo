package com.abm.bloggerdemo.mapper;

import com.abm.bloggerdemo.dto.request.CategorySaveRequestDto;
import com.abm.bloggerdemo.dto.response.CategoryFindAllResponseDto;
import com.abm.bloggerdemo.entity.Categories;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Categories categorySaveRequestDtoToCategory(CategorySaveRequestDto categorySaveRequestDto);
    CategoryFindAllResponseDto  categoriesFindAllResponseDtoToCategory(Categories categories);
}
