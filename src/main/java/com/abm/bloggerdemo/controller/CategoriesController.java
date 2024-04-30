package com.abm.bloggerdemo.controller;

import static com.abm.bloggerdemo.constant.EndPoints.*;

import com.abm.bloggerdemo.dto.request.CategorySaveRequestDto;
import com.abm.bloggerdemo.dto.request.PostSaveRequestDto;
import com.abm.bloggerdemo.dto.response.CategoryFindAllResponseDto;
import com.abm.bloggerdemo.dto.response.PostFindAllResponseDto;
import com.abm.bloggerdemo.service.CategoriesService;
import com.abm.bloggerdemo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ROOT+CATEGORIES)
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;
    private final PostService postService;

    @PostMapping(SAVE)
    @CrossOrigin("*")
    public ResponseEntity<String> saveCategory(@RequestBody CategorySaveRequestDto categorySaveRequestDto) {
        categoriesService.categorySaveDto(categorySaveRequestDto);
        return ResponseEntity.ok("Category save başarılı");
    }

    @GetMapping(FINDALL)
    @CrossOrigin("*")
    public ResponseEntity<List<CategoryFindAllResponseDto>> findAllDto() {
        return ResponseEntity.ok(categoriesService.findCategoryDto());
    }

    @GetMapping(FINDBYID)
    @CrossOrigin("*")
    public ResponseEntity<CategoryFindAllResponseDto> findAllCategoriesById(@RequestParam Long id) {
        return ResponseEntity.ok(categoriesService.findCategoryById(id));
    }

    @PutMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<String> updateCategoryById(Long id,  String name, String description) {
        return ResponseEntity.ok(categoriesService.updateCategoryById(id, name, description));
    }

    @DeleteMapping(DELETE)
    @CrossOrigin("*")
    public ResponseEntity<String> deleteCategoryById(@RequestParam Long id) {
        return ResponseEntity.ok(categoriesService.deleteCategoryById(id));
    }
    @GetMapping(FINDALLBYCATEGORIESCONTAINING)
    @CrossOrigin("*")
    public ResponseEntity<List<CategoryFindAllResponseDto>> findAllCategoriesByNameContainingIgnoreCase(String content){
        return ResponseEntity.ok(categoriesService.findAllCategoriesByNameContainingIgnoreCase(content));
    }



}
