package com.example.nailshopbackend.security.jwt;

import com.example.nailshopbackend.common.exception.GlobalException;
import com.example.nailshopbackend.security.dto.TokenDTO;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


@Component
public class TokenProvider {
   private final Logger logger = (Logger) LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; // 1초
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14; // 2주일

    private Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
    * @작성일 : 2021-12-30
    * @작성자 : jylim
    * @변경이력 :
    * @Method 설명 : 토큰 생성
    * return
    */
    public TokenDTO createToken(org.springframework.security.core.Authentication authentication){

        //권한 가져오기
        String authorities = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        //Access Token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
        .setSubject(authentication.getName())
        .claim(AUTHORITIES_KEY, authorities)
        .setExpiration(accessTokenExpiresIn)
        .signWith(key,SignatureAlgorithm.HS512)
        .compact();

        //Refresh Token 생성
        Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);
        String refreshToken = Jwts.builder()
        .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
        .signWith(key, SignatureAlgorithm.HS512)
        .compact();

        return TokenDTO.builder()
        .grantType(BEARER_TYPE)
        .accessToken(accessToken)
        .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
        .refreshTokenExpiresIn(refreshTokenExpiresIn.getTime())
        .refreshToken(refreshToken)
        .build();
     }

     //토큰의 Authentication 객체 반환
     public Authentication getAuthentication(String accessToken){
        //토큰 복호화
        Claims claims = parseClaims(accessToken);
        if(claims.get(AUTHORITIES_KEY) == null){
            throw new GlobalException(HttpStatus.BAD_REQUEST, "권한 정보가 없는 토큰입니다.");
        }

        //Claims에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());

        //UserDetails 객체 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(),  "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
        }

    //토큰 검증
     public boolean validateToken(String token){
         try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
         } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            logger.info("잘못된 JWT 서명입니다.");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "잘못된 JWT 서명");
         } catch (ExpiredJwtException e){
            logger.info("만료된 JWT 토큰입니다");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "만료된 토큰입니다");
         } catch (UnsupportedJwtException e){
            logger.info("지원되지 않는 JWT 토큰입니다");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "지원되지 않는 JWT 토큰");
         } catch (IllegalArgumentException e){
            logger.info("JWT 토큰이 잘못되었습니다");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "JWT 토큰이 잘못되었습니다");
         } catch (Exception exception){
            throw new GlobalException(HttpStatus.BAD_REQUEST, "토큰 인증 에러");
         }
     }

     private Claims parseClaims(String accessToken){
         try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
         } catch (ExpiredJwtException e){
            return e.getClaims();
         }
     }
 }
