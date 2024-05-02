package com.abm.bloggerdemo.dto.request;

public record CommentSaveRequestDto(String content,Long user_id, Long post_id) {
}
