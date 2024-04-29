package com.abm.bloggerdemo.dto.response;

import java.util.List;

public record CategoryFindAllResponseDto(String name, String description, List<UserPostFindAllResponseDto>posts) {
}
