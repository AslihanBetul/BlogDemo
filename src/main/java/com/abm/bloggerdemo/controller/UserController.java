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
        return ResponseEntity.ok(userService.findUserDto());
    }

    @GetMapping(FINDBYNAMEANDLASTNAME)
    @CrossOrigin("*")
    public ResponseEntity<UserFindAllResponseDto> findByNameAndLastNameDto(@RequestParam String name, @RequestParam String lastname){
        return ResponseEntity.ok(userService.findUserDto2(name,lastname));
    }


    @GetMapping(FINDBYID)
    @CrossOrigin("*")
    public ResponseEntity<UserFindAllResponseDto> findByIdDto(@RequestParam Long id){

        return ResponseEntity.ok(userService.findUserDtoID(id));
    }




}
