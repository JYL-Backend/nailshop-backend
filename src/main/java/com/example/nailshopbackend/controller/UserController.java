package com.example.nailshopbackend.controller;

import com.example.nailshopbackend.common.validation.ValidationGroups;
import com.example.nailshopbackend.dto.request.RequestLoginDTO;
import com.example.nailshopbackend.dto.request.RequestRegisterDTO;
import com.example.nailshopbackend.security.dto.TokenDTO;
import com.example.nailshopbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FileName : RegisterController.java
 * @Date : 2022-01-25
 * @작성자 : jylim
 * @변경이력 :
 * @프로그램 설명 :  유저 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    /**
     * @작성일 : 2022-01-25
     * @작성자 : jylim
     * @변경이력 :
     * @Method 설명 : 회원가입
     * return
    */
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated(ValidationGroups.Insert.class) RequestRegisterDTO dto){
        return ResponseEntity.ok().body(userService.register(dto));
    }

    /**
     * @작성일 : 2022-01-25
     * @작성자 : jylim
     * @변경이력 :
     * @Method 설명 : 로그인
     * return
    */
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody RequestLoginDTO dto){
        return ResponseEntity.ok().body(userService.login(dto));
    }
}
