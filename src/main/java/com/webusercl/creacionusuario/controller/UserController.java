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
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }


    @Operation(summary = "Endpoint que  permite crear un nuevo usuario", description = "Endpoint que  permite crear un nuevo usuario")
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


    @Operation(summary = "Endpoint que lista todos los usuarios registrados", description = "Endpoint que lista todos los usuarios registrados")
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }


}
