package com.example.nailshopbackend.security.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenDTO {
    String grantType;
    String accessToken;
    long accessTokenExpiresIn;
    String refreshToken;
    long refreshTokenExpiresIn;
}
