package com.abm.bloggerdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    USER_NOT_FOUND(5001,"Boyle bir USER bulunamadi. ", HttpStatus.NOT_FOUND),
    POST_NOT_FOUND(5002,"Post not found!",HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND(5003,"Category not found!",HttpStatus.NOT_FOUND),
    WRONG_EMAIL_TYPE(5004,"Wrong email type!", HttpStatus.UNAUTHORIZED),
    WRONG_PASSWORD_LENGHT(5005,"Wrong password lenght!", HttpStatus.LENGTH_REQUIRED);

    private Integer code;
    private String message;
    private HttpStatus httpStatus;
}
