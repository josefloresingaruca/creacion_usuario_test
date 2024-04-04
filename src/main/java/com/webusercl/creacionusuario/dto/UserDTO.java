package com.webusercl.creacionusuario.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class UserDTO {

    private String name;
    private String email;
    private String password;
    private Set<PhoneDTO> phones;
}
