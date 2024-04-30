package com.abm.bloggerdemo.service;

import com.abm.bloggerdemo.dto.request.CategorySaveRequestDto;
import com.abm.bloggerdemo.dto.request.UserSaveRequestDto;
import com.abm.bloggerdemo.dto.response.CategoryFindAllResponseDto;
import com.abm.bloggerdemo.dto.response.PostFindAllResponseDto;
import com.abm.bloggerdemo.entity.Categories;
import com.abm.bloggerdemo.exception.BloggerDemoAppException;
import com.abm.bloggerdemo.exception.ErrorType;
import com.abm.bloggerdemo.mapper.CategoryMapper;
import com.abm.bloggerdemo.mapper.PostMapper;
import com.abm.bloggerdemo.mapper.UserMapper;
import com.abm.bloggerdemo.repository.CategoriesRepository;
import com.abm.bloggerdemo.repository.PostRepository;
import com.abm.bloggerdemo.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesService  extends ServiceManager<Categories,Long> {
    private final CategoriesRepository categoriesRepository;
    private final PostRepository postRepository;

    public CategoriesService(CategoriesRepository categoriesRepository, PostRepository postRepository) {
        super(categoriesRepository);
        this.categoriesRepository = categoriesRepository;
        this.postRepository = postRepository;
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
        Optional<Categories> byId = categoriesRepository.findById(id);
        if (byId.isEmpty()){
            throw new BloggerDemoAppException(ErrorType.CATEGORY_NOT_FOUND,"Category  not found");
        }

        return CategoryMapper.INSTANCE.categoriesFindAllResponseDtoToCategory(categoriesRepository.findById(id).get());
    }

    public String updateCategoryById(Long id, String name,String description){
        Optional<Categories> byId = categoriesRepository.findById(id);
        if (byId.isEmpty()){
            throw new BloggerDemoAppException(ErrorType.CATEGORY_NOT_FOUND,"Category  not found");
        }
        Categories categories = byId.get();
        categories.setName(name);
        categories.setDescription(description);
        categoriesRepository.save(categories);
        return "Güncelleme Basarili";
    }

    public String deleteCategoryById(Long id){
        Optional<Categories> byId = categoriesRepository.findById(id);
        if (byId.isEmpty()){
            throw new BloggerDemoAppException(ErrorType.CATEGORY_NOT_FOUND,"Category  not found");
        }
        categoriesRepository.deleteById(id);
        return "Silme Basarili";
    }

    //kategorinin ismine göre getirme

    public List<CategoryFindAllResponseDto> findAllCategoriesByNameContainingIgnoreCase(String containing){
        List<Categories> allCategoriesByNameContainingIgnoreCase = categoriesRepository.findAllCategoriesByNameContainingIgnoreCase(containing);
        if (allCategoriesByNameContainingIgnoreCase.isEmpty()){
            throw new BloggerDemoAppException(ErrorType.CATEGORY_NOT_FOUND,"Category  not found");
        }
        return categoriesRepository
                .findAllCategoriesByNameContainingIgnoreCase(containing)
                .stream()
                .map(CategoryMapper.INSTANCE::categoriesFindAllResponseDtoToCategory)
                .collect(Collectors.toList());
    }




}
