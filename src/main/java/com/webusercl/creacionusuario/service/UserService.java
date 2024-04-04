package com.webusercl.creacionusuario.service;

import com.webusercl.creacionusuario.dto.UserCreateDTO;
import com.webusercl.creacionusuario.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listUsers();
    UserCreateDTO saveUser(UserDTO userDTO);
}
