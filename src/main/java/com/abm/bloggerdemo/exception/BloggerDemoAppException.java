package com.abm.bloggerdemo.exception;

import lombok.Getter;


@Getter
public class BloggerDemoAppException extends RuntimeException {
    private ErrorType errorType;

    public BloggerDemoAppException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public BloggerDemoAppException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType = errorType;
    }


}
