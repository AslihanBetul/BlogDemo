package com.abm.bloggerdemo.dto.response;

import java.time.LocalDate;
import java.util.List;

public record PostFindAllResponseDto(String title, String content, LocalDate published_at, Long user_id, Long categories_id,
                                     List<CommentDto> comments) {
}
