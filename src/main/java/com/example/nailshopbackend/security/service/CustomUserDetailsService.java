package com.example.nailshopbackend.security.service;


import com.example.nailshopbackend.authority.entity.QAuthorityEntity;
import com.example.nailshopbackend.common.exception.GlobalException;
import com.example.nailshopbackend.user.entity.QUserEntity;
import com.example.nailshopbackend.user.entity.UserEntity;
import com.example.nailshopbackend.user.repository.UserRepository;
import com.example.nailshopbackend.userauthority.entity.QUserAuthorityEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.nailshopbackend.authority.entity.QAuthorityEntity.authorityEntity;
import static com.example.nailshopbackend.user.entity.QUserEntity.userEntity;
import static com.example.nailshopbackend.userauthority.entity.QUserAuthorityEntity.userAuthorityEntity;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService {

    private final JPAQueryFactory queryFactory;

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            List<UserEntity> result = queryFactory.select(userEntity).from(userEntity)
                    .leftJoin(userEntity, userAuthorityEntity.user).fetchJoin()
                    .leftJoin(userAuthorityEntity.authority, authorityEntity).fetchJoin()
                    .where(userEntity.email.eq(username)).fetch();
                if(result.isEmpty()){
                    throw new GlobalException(HttpStatus.BAD_REQUEST,"존재하지 않는 유저입니다");
                }
                return createUser(username,result.get(0));
        }

            private User createUser(String username, UserEntity userEntity){
                List<GrantedAuthority> grantedAuthorities = userEntity.getAuthorities().stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getAuthorityName()))
                        .collect(Collectors.toList());

                return new User(userEntity.getEmail(), userEntity.getPassword(), grantedAuthorities);
        }
}

