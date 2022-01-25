package com.example.nailshopbackend.dto.request;

import com.example.nailshopbackend.common.user.entity.UserEntity;
import com.example.nailshopbackend.common.validation.ValidationGroups;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @FileName : RequestRegisterDTO.java
 * @Date : 2022-01-25
 * @작성자 : jylim
 * @변경이력 :
 * @프로그램 설명 :  회원가입 요청 DTO
 */
@Getter
public class RequestRegisterDTO {
    @NotBlank(groups = {ValidationGroups.Insert.class,ValidationGroups.Update.class}, message = "이메일은 필수 값입니다")
    private String email;

    @NotBlank(groups = {ValidationGroups.Insert.class}, message = "비밀번호는 필수 값입니다")
    private String password;

    @NotBlank(groups = {ValidationGroups.Insert.class}, message = "비밀번호는 필수 값입니다")
    private String passwordConfirm;

    @NotBlank(groups = {ValidationGroups.Insert.class,ValidationGroups.Update.class}, message = "이름은 필수 값입니다")
    private String name;
    
    @NotBlank(groups = {ValidationGroups.Insert.class,ValidationGroups.Update.class}, message = "휴대폰 번호는 필수 값입니다")
    private String phoneNumber;

    /**
     * @FileName : RequestRegisterDTO.java
     * @Date : 2022-01-25
     * @작성자 : jylim
     * @변경이력 :
     * @프로그램 설명 :  dto -> entity
     */
    public UserEntity toUserEntity() {
        UserEntity user = UserEntity.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .build();
        return user;
    }
}
