package com.webusercl.creacionusuario.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webusercl.creacionusuario.dto.UserCreateDTO;
import com.webusercl.creacionusuario.dto.UserDTO;
import com.webusercl.creacionusuario.exception.EmailExistsException;
import com.webusercl.creacionusuario.exception.InvalidEmailFormatException;
import com.webusercl.creacionusuario.exception.InvalidPasswordFormatException;
import com.webusercl.creacionusuario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/users")
    public ResponseEntity<UserCreateDTO> createUser(@RequestBody String json) {
        try {
            UserDTO userDTO = objectMapper.readValue(json, UserDTO.class);
            UserCreateDTO responseDTO =  userService.saveUser(userDTO);

            return ResponseEntity.ok(responseDTO);

        } catch (InvalidEmailFormatException e) {
            e.printStackTrace();
            throw e;
        } catch (InvalidPasswordFormatException e) {
            e.printStackTrace();
            throw e;
        } catch (EmailExistsException e) {
            e.printStackTrace();
            throw e;
        } catch (JsonMappingException e ) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/users")
    public List<UserDTO> listUsers() {
        return userService.listUsers();
    }


}
