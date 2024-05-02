package com.abm.bloggerdemo.controller;

import com.abm.bloggerdemo.dto.request.CommentSaveRequestDto;
import com.abm.bloggerdemo.dto.response.CommentFindAllResponseDto;
import com.abm.bloggerdemo.dto.response.PostFindAllResponseDto;
import com.abm.bloggerdemo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.abm.bloggerdemo.constant.EndPoints.*;

import java.lang.reflect.GenericDeclaration;
import java.util.List;

@RestController
@RequestMapping(ROOT+COMMENT)
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @PostMapping(SAVE)
    @CrossOrigin("*")
    public ResponseEntity<String> save(@RequestBody CommentSaveRequestDto dto){
        commentService.commentSaveDto(dto);
        return ResponseEntity.ok("Kayit Basarili");
    }

    @GetMapping(FINDALL)
    @CrossOrigin("*")
    public ResponseEntity<List<CommentFindAllResponseDto>> findAllDto(){
        return ResponseEntity.ok(commentService.findCommentDto());
    }

}
