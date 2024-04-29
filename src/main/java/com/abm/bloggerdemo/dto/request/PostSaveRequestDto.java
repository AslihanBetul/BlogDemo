package com.abm.bloggerdemo.dto.request;

public record PostSaveRequestDto(String title,String content,Long user_id,Long categories_id) {
}
