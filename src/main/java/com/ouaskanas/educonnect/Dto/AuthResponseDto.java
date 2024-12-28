package com.ouaskanas.educonnect.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthResponseDto {
    private String token;
    private String tokenType ="Bearer ";
    private static final String errorMessage ="something went wrong";

    public AuthResponseDto(String token) {
        this.token = token;
    }

}
