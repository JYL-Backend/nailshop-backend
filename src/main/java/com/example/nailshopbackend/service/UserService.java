package com.example.nailshopbackend.service;

import com.example.nailshopbackend.common.authority.entity.AuthorityEntity;
import com.example.nailshopbackend.common.exception.ErrorResult;
import com.example.nailshopbackend.common.exception.GlobalException;
import com.example.nailshopbackend.common.user.entity.QUserEntity;
import com.example.nailshopbackend.common.user.entity.UserEntity;
import com.example.nailshopbackend.common.user.repository.UserRepository;
import com.example.nailshopbackend.common.userauthority.entity.UserAuthorityEntity;
import com.example.nailshopbackend.dto.request.RequestLoginDTO;
import com.example.nailshopbackend.dto.request.RequestRegisterDTO;
import com.example.nailshopbackend.dto.response.ResponseRegisterDTO;
import com.example.nailshopbackend.repository.UserAuthorityRepository;
import com.example.nailshopbackend.security.dto.TokenDTO;
import com.example.nailshopbackend.security.jwt.TokenProvider;
import com.example.nailshopbackend.security.refreshtoken.entity.RefreshTokenEntity;
import com.example.nailshopbackend.security.refreshtoken.repository.RefreshTokenRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.nailshopbackend.common.user.entity.QUserEntity.userEntity;

/**
 * @FileName : UserService.java
 * @Date : 2022-01-25
 * @작성자 : jylim
 * @변경이력 :
 * @프로그램 설명 :  유저 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final JPAQueryFactory queryFactory;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;


    /**
     * @작성일 : 2022-01-25
     * @작성자 : jylim
     * @변경이력 :
     * @Method 설명 : 회원가입
     * return
    */
    public ResponseRegisterDTO register(RequestRegisterDTO dto) {
        // 이미 존재하는 이메일
        if(queryFactory.selectFrom(userEntity).where(userEntity.email.eq(dto.getEmail())).fetch().size() != 0){
            throw new GlobalException(ErrorResult.IS_SET_EMAIL);
        }

        UserEntity userEntity = dto.toUserEntity();
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(userEntity);

        // 최초 유저권한
        AuthorityEntity roleEntity = AuthorityEntity.builder().authorityName("ROLE_USER").build();
        UserAuthorityEntity userAutority = UserAuthorityEntity.builder().authority(roleEntity).user(userEntity).build();

        userAuthorityRepository.save(userAutority);

        return ResponseRegisterDTO.getDto(userEntity);
    }

    public TokenDTO login(RequestLoginDTO dto) {
        // id/pwd 기반 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

        // 사용자 암호 체크
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 인증정보 기반 JWT 생성
        TokenDTO tokenDTO = tokenProvider.createToken(authentication);

        // Refresh Token 저장
        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
                .email(authentication.getName())
                .token(tokenDTO.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 토큰 반환
        return tokenDTO;
    }
}
