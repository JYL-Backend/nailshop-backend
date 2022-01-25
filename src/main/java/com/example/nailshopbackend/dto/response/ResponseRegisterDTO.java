package com.example.nailshopbackend.dto.response;

import com.example.nailshopbackend.common.user.entity.UserEntity;
import com.example.nailshopbackend.common.validation.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRegisterDTO {
    private String email;

    private String name;

    private String phoneNumber;

    public static ResponseRegisterDTO getDto(UserEntity userEntity) {
        ResponseRegisterDTO dto = ResponseRegisterDTO.builder()
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
        return dto;
    }
}
