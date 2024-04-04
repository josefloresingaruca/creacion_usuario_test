package com.webusercl.creacionusuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webusercl.creacionusuario.controller.UserController;
import com.webusercl.creacionusuario.dto.PhoneDTO;
import com.webusercl.creacionusuario.dto.UserCreateDTO;
import com.webusercl.creacionusuario.dto.UserDTO;
import com.webusercl.creacionusuario.exception.EmailExistsException;
import com.webusercl.creacionusuario.exception.InvalidEmailFormatException;
import com.webusercl.creacionusuario.exception.InvalidPasswordFormatException;
import com.webusercl.creacionusuario.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private UserController userController;

    @Test
    public void testCreateUser_Success() throws Exception {

        //Bateria de Datos
        String json = "{\n" +
                "\"name\": \"Juan Rodriguez\",\n" +
                "\"email\": \"juanflo@dominio.cl\",\n" +
                "\"password\": \"ADT_2024jf\",\n" +
                "\"phones\": [\n" +
                "{\n" +
                "\"number\": \"1234567\",\n" +
                "\"citycode\": \"1\",\n" +
                "\"contrycode\": \"57\"\n" +
                "}\n" +
                "]\n" +
                "}";
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Juan Rodriguez");
        userDTO.setEmail("juanflo@dominio.cl");
        userDTO.setPassword("ADT_2024jf");

        Set<PhoneDTO> listPhone = new HashSet<>();
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber("1234567");
        phoneDTO.setCitycode("citycode");
        phoneDTO.setContrycode("contrycode");
        listPhone.add(phoneDTO);
        userDTO.setPhones(listPhone);


        // Respuesta esperada
        UserCreateDTO expectedResponseDTO = new UserCreateDTO();
        expectedResponseDTO.setId(UUID.randomUUID());
        expectedResponseDTO.setCreated("2024-04-03 23:58:01");
        expectedResponseDTO.setLastLogin("2024-04-03 23:58:01");
        expectedResponseDTO.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuZmxvQGRvbWluaW8uY2wifQ.Ht4RbbSZOPzMBApAl21eB9KSTvZsFrEqkkzFfDwwlYneAURVU5PMtDaF-ND7RHxrwB7zU90zZ_Fq4W8T2VgFMA");
        expectedResponseDTO.setIsactive("activo");

        // Configuración de mocks
        when(objectMapper.readValue(json, UserDTO.class)).thenReturn(userDTO);
        when(userService.saveUser(userDTO)).thenReturn(expectedResponseDTO);

        // Act
        ResponseEntity<UserCreateDTO> response = userController.createUser(json);

        // Assert
        assertEquals(expectedResponseDTO, response.getBody());
        assertEquals(200, response.getStatusCodeValue());

        // Verificar que se llamó al método saveUser del userService con el userDTO
        verify(userService, times(1)).saveUser(userDTO);
    }

    @Test
    public void testCreateUser_EmailExistsException() throws Exception {
        // Bateria de Datos
        String json = "{\n" +
                "\"name\": \"Juan Rodriguez\",\n" +
                "\"email\": \"juanflo@dominio.cl\",\n" +
                "\"password\": \"ADT_2024jf\",\n" +
                "\"phones\": [\n" +
                "{\n" +
                "\"number\": \"1234567\",\n" +
                "\"citycode\": \"1\",\n" +
                "\"contrycode\": \"57\"\n" +
                "}\n" +
                "]\n" +
                "}";
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Juan Rodriguez");
        userDTO.setEmail("juanflo@dominio.cl");
        userDTO.setPassword("ADT_2024jf");

        Set<PhoneDTO> listPhone = new HashSet<>();
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber("1234567");
        phoneDTO.setCitycode("citycode");
        phoneDTO.setContrycode("contrycode");
        listPhone.add(phoneDTO);
        userDTO.setPhones(listPhone);

        // Configuración de mocks
        when(objectMapper.readValue(json, UserDTO.class)).thenReturn(userDTO);
        when(userService.saveUser(userDTO)).thenThrow(new EmailExistsException("El correo ya está registrado"));

        // Act
        EmailExistsException exception = Assertions.assertThrows(EmailExistsException.class, () -> {
            userController.createUser(json);
        });

        // Assert
        Assertions.assertTrue(exception.getMessage().contains("El correo ya está registrado"));
    }

    @Test
    public void testCreateUser_InvalidEmailFormatException() throws Exception {
        // Bateria de Datos
        String json = "{\n" +
                "\"name\": \"Juan Rodriguez\",\n" +
                "\"email\": \"correo_sin_formato\",\n" + // Correo sin formato correcto
                "\"password\": \"ADT_2024jf\",\n" +
                "\"phones\": [\n" +
                "{\n" +
                "\"number\": \"1234567\",\n" +
                "\"citycode\": \"1\",\n" +
                "\"contrycode\": \"57\"\n" +
                "}\n" +
                "]\n" +
                "}";
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Juan Rodriguez");
        userDTO.setEmail("correo_sin_formato"); // Correo sin formato correcto
        userDTO.setPassword("ADT_2024jf");

        Set<PhoneDTO> listPhone = new HashSet<>();
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber("1234567");
        phoneDTO.setCitycode("citycode");
        phoneDTO.setContrycode("contrycode");
        listPhone.add(phoneDTO);
        userDTO.setPhones(listPhone);

        // Configuración de mocks
        when(objectMapper.readValue(json, UserDTO.class)).thenReturn(userDTO);
        when(userService.saveUser(userDTO)).thenThrow(new InvalidEmailFormatException("El formato del correo electrónico es inválido"));

        // Act
        InvalidEmailFormatException exception = Assertions.assertThrows(InvalidEmailFormatException.class, () -> {
            userController.createUser(json);
        });

        // Assert
        Assertions.assertTrue(exception.getMessage().contains("El formato del correo electrónico es inválido"));
    }

    @Test
    public void testCreateUser_InvalidPasswordFormatException() throws Exception {
        // Bateria de Datos
        String json = "{\n" +
                "\"name\": \"Juan Rodriguez\",\n" +
                "\"email\": \"juanflo@dominio.cl\",\n" +
                "\"password\": \"invalid_password123\",\n" + // Contraseña con formato inválido
                "\"phones\": [\n" +
                "{\n" +
                "\"number\": \"1234567\",\n" +
                "\"citycode\": \"1\",\n" +
                "\"contrycode\": \"57\"\n" +
                "}\n" +
                "]\n" +
                "}";
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Juan Rodriguez");
        userDTO.setEmail("juanflo@dominio.cl");
        userDTO.setPassword("invalid_password123"); // Contraseña con formato inválido

        Set<PhoneDTO> listPhone = new HashSet<>();
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber("1234567");
        phoneDTO.setCitycode("citycode");
        phoneDTO.setContrycode("contrycode");
        listPhone.add(phoneDTO);
        userDTO.setPhones(listPhone);

        // Configuración de mocks
        when(objectMapper.readValue(json, UserDTO.class)).thenReturn(userDTO);
        when(userService.saveUser(userDTO)).thenThrow(new InvalidPasswordFormatException("El formato de la contraseña es inválido"));

        // Act
        InvalidPasswordFormatException exception = Assertions.assertThrows(InvalidPasswordFormatException.class, () -> {
            userController.createUser(json);
        });

        // Assert
        Assertions.assertTrue(exception.getMessage().contains("El formato de la contraseña es inválido"));
    }
}
