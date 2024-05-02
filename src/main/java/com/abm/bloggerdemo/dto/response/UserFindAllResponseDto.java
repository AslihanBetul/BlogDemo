package com.abm.bloggerdemo.dto.response;

import java.util.List;

public record UserFindAllResponseDto(String name, String lastname, List<UserPostFindAllResponseDto> posts, List<CommentDto> comments) {
}
