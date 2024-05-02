package com.abm.bloggerdemo.controller;

import com.abm.bloggerdemo.dto.request.UserSaveRequestDto;
import com.abm.bloggerdemo.dto.response.UserFindAllResponseDto;
import com.abm.bloggerdemo.exception.BloggerDemoAppException;
import com.abm.bloggerdemo.exception.ErrorType;
import com.abm.bloggerdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.abm.bloggerdemo.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT+USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(SAVE)@CrossOrigin("*")
    public ResponseEntity<String> save(@RequestBody UserSaveRequestDto dto){
        if (!dto.email().endsWith("@gmail.com")) {

            throw new BloggerDemoAppException(ErrorType.WRONG_EMAIL_TYPE,"Wrong email Type");

        } else if (dto.password().length() != 8) {

            throw new BloggerDemoAppException(ErrorType.WRONG_PASSWORD_LENGHT,"Wrong password length");

    } else {
            userService.userSaveDto(dto);

            return ResponseEntity.ok("Kayit Basarili");    }
    }




    @GetMapping(FINDALL)
    @CrossOrigin("*")
    public ResponseEntity<List<UserFindAllResponseDto>> findAllDto(){
        ResponseEntity<List<UserFindAllResponseDto>> listResponseEntity = ResponseEntity.ok(userService.findUserDto());
        if (listResponseEntity == null) {
            throw new BloggerDemoAppException(ErrorType.USER_NOT_FOUND, "User not found");

        }
           return listResponseEntity;

    }




    @GetMapping(FINDBYNAMEANDLASTNAME)
    @CrossOrigin("*")
    public ResponseEntity<UserFindAllResponseDto> findUserByNameAndLastName(@RequestParam String name, @RequestParam String lastname){
        ResponseEntity<UserFindAllResponseDto> dtoResponseEntity = ResponseEntity.ok(userService.findUserDto2(name, lastname));
        if (dtoResponseEntity.getBody()==null) {
            throw new BloggerDemoAppException(ErrorType.USER_NOT_FOUND, "User not found");
        }
        return dtoResponseEntity;
    }

    @GetMapping(FINDBYID)
    @CrossOrigin("*")
    public ResponseEntity<UserFindAllResponseDto> findByIdDto(@RequestParam Long id){

        return ResponseEntity.ok(userService.findUserDtoID(id));
    }

    @PutMapping(UPDATE)
    @CrossOrigin("*")
   public ResponseEntity<String>updateUser(Long id,String name,String lastName,String email,String password){
        return ResponseEntity.ok(userService.updateUser(id,name,lastName,email,password));
   }

    @DeleteMapping(DELETE)
    @CrossOrigin("*")
    public ResponseEntity<String>deleteUser(Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }
//post like etme
    @PostMapping("/saveLike")
    @CrossOrigin("*")
    public ResponseEntity<String> userLikePost(@RequestParam Long userId, @RequestParam Long postId) {
        userService.userLikePost(userId, postId);
        return ResponseEntity.ok("Post liked");
    }

    //post unlike etme :
    @DeleteMapping("/saveUnlike")
    @CrossOrigin("*")
    public ResponseEntity<String> userUnlikePost(@RequestParam Long userId, @RequestParam Long postId) {
        userService.userUnlikePost(userId, postId);
        return ResponseEntity.ok("Post unliked");
    }



}
