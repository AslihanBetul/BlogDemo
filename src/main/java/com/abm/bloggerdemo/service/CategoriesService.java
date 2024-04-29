package com.abm.bloggerdemo.service;

import com.abm.bloggerdemo.dto.request.CategorySaveRequestDto;
import com.abm.bloggerdemo.dto.request.UserSaveRequestDto;
import com.abm.bloggerdemo.dto.response.CategoryFindAllResponseDto;
import com.abm.bloggerdemo.dto.response.PostFindAllResponseDto;
import com.abm.bloggerdemo.entity.Categories;
import com.abm.bloggerdemo.mapper.CategoryMapper;
import com.abm.bloggerdemo.mapper.PostMapper;
import com.abm.bloggerdemo.mapper.UserMapper;
import com.abm.bloggerdemo.repository.CategoriesRepository;
import com.abm.bloggerdemo.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService  extends ServiceManager<Categories,Long> {
    private final CategoriesRepository categoriesRepository;
    public CategoriesService(CategoriesRepository categoriesRepository) {
        super(categoriesRepository);
        this.categoriesRepository = categoriesRepository;
    }

    public void categorySaveDto(CategorySaveRequestDto dto) {
       categoriesRepository.save(CategoryMapper.INSTANCE.categorySaveRequestDtoToCategory(dto));
    }


    public List<CategoryFindAllResponseDto> findCategoryDto() {
        List<CategoryFindAllResponseDto> categoryFindAllResponseDtos = new ArrayList<>();

        findAll().forEach(categories -> {
            categoryFindAllResponseDtos.add(CategoryMapper.INSTANCE.categoriesFindAllResponseDtoToCategory(categories));
        });
        return categoryFindAllResponseDtos;
    }

    public CategoryFindAllResponseDto findCategoryById(Long id){
        return CategoryMapper.INSTANCE.categoriesFindAllResponseDtoToCategory(categoriesRepository.findById(id).get());
    }

}
