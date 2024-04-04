package com.webusercl.creacionusuario.service;

import com.webusercl.creacionusuario.dto.UserCreateDTO;
import com.webusercl.creacionusuario.dto.UserDTO;
import com.webusercl.creacionusuario.exception.EmailExistsException;
import com.webusercl.creacionusuario.exception.InvalidEmailFormatException;
import com.webusercl.creacionusuario.exception.InvalidPasswordFormatException;
import com.webusercl.creacionusuario.model.User;
import com.webusercl.creacionusuario.repository.UserRepository;
import com.webusercl.creacionusuario.utils.Constans;
import com.webusercl.creacionusuario.utils.Mappers;
import com.webusercl.creacionusuario.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class UserServiceImpl  implements UserService{

    @Value("${email.regex}")
    private String emailRegex;
    @Value("${password.regex}")
    private String passwordRegex;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository ){
        this.userRepository=userRepository;
    }



    @Override
    public UserCreateDTO saveUser(UserDTO userDTO) {
        User user = Mappers.mapToUser(userDTO);

        Optional<User> exitsMail= userRepository.findByEmail(user.getEmail());
        if (exitsMail.isPresent()){
            System.out.println("Existe correo no debe regstrrar");
            throw new EmailExistsException("Correo existe");
        }

        String email = user.getEmail();
        if (!email.matches(emailRegex)) {
            System.out.println("Mostrar excepción, el correo no tiene el formato adecuado");
            throw new InvalidEmailFormatException("El formato del correo electrónico no es válido");
        }

        String password = user.getPassword();
        if (password.matches(passwordRegex)){
            System.out.println("formato no adecuado para el password");
            throw new InvalidPasswordFormatException("El formato del password no es válido");
        }

        LocalDateTime now = LocalDateTime.now();
        Date dateCreate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        user.setCreated(dateCreate);
        user.setLastLogin(dateCreate);
        user.setIsactive(Constans.ACTIVE);
        user.setToken(Utils.generateJwtToken(user.getEmail()));
        User userBD=  userRepository.save(user);
        return Mappers.mapToUserCreateDTO(userBD);
    }


    @Override
    public List<UserDTO> listUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());
    }



    public UserDTO mapToUserDTO(User user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreated(dateFormat.format(user.getCreated()));
        userDTO.setModified(user.getModified() != null ? dateFormat.format(user.getModified()) : null);
        userDTO.setLastLogin(dateFormat.format(user.getLastLogin()));
        userDTO.setIsactive(user.getIsactive());
        userDTO.setToken(user.getToken());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhones(Mappers.mapToPhoneDTOSet(user.getPhones()));
        return userDTO;
    }



}
