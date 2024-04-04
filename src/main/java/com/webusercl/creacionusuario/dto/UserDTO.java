package com.webusercl.creacionusuario.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
public class UserDTO {

    private String name;
    private String email;
    private String password;

    private String created;
    private String modified;
    private String lastLogin;

    private Integer isactive;

    private String token;


    private Set<PhoneDTO> phones;
}
