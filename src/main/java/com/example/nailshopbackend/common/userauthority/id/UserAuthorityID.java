package com.example.nailshopbackend.common.userauthority.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthorityID implements Serializable {
    private String user;
    private String authority;
}
