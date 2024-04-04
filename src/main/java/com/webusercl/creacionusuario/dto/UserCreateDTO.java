package com.webusercl.creacionusuario.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserCreateDTO {

    private UUID id;
    private String created;
    private String modified;
    private String lastLogin;
    private String token;
    private String isactive;
}
