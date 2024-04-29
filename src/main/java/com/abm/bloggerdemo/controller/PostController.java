package com.abm.bloggerdemo.controller;

import com.abm.bloggerdemo.dto.request.PostSaveRequestDto;
import com.abm.bloggerdemo.dto.response.PostFindAllResponseDto;
import com.abm.bloggerdemo.dto.response.UserFindAllResponseDto;
import com.abm.bloggerdemo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.abm.bloggerdemo.constant.EndPoints.*;
@RestController
@RequestMapping(ROOT+POST)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;



    @GetMapping(FINDALL)
    @CrossOrigin("*")
    public ResponseEntity<List<PostFindAllResponseDto>> findAllDto(){
        return ResponseEntity.ok(postService.findPostDto());
    }

    @PostMapping(SAVE)
    @CrossOrigin("*")
    public ResponseEntity<String> savePost2(@RequestBody PostSaveRequestDto postSaveRequestDto){
        postService.savePost1(postSaveRequestDto);
        return ResponseEntity.ok("Post saved successfully");
    }

    @GetMapping(FINDBYPOSTID)
    @CrossOrigin("*")
    public ResponseEntity<PostFindAllResponseDto> findByIdDto(@RequestParam Long id){
        return ResponseEntity.ok(postService.findPostDtoID(id));
    }

    @GetMapping(FINDBYUSERID)
    @CrossOrigin("*")
    public ResponseEntity<List<PostFindAllResponseDto>> findAllPostByUserId(@RequestParam Long userId){
        return ResponseEntity.ok(postService.findAllPostByUserId(userId));
    }

    @GetMapping(FINDBYCATEGORIESID)
    @CrossOrigin("*")
    public ResponseEntity<List<PostFindAllResponseDto>> findAllPostByCategoriesId(@RequestParam Long categoriesId){
        return ResponseEntity.ok(postService.findAllPostByCategoriesId(categoriesId));
    }

}


