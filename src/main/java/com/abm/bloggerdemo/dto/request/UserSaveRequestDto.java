package com.abm.bloggerdemo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserSaveRequestDto(String name, String lastname, @Email String email, @Size(min = 8,max = 8) String password) {
}
