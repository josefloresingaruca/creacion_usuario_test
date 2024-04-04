package com.webusercl.creacionusuario.utils;

import com.webusercl.creacionusuario.dto.PhoneDTO;
import com.webusercl.creacionusuario.dto.UserCreateDTO;
import com.webusercl.creacionusuario.dto.UserDTO;
import com.webusercl.creacionusuario.model.Phone;
import com.webusercl.creacionusuario.model.User;

import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Mappers {



    public static Set<PhoneDTO> mapToPhoneDTOSet(Set<Phone> phones) {
        return phones.stream()
                .map(phone -> {
                    PhoneDTO phoneDTO = new PhoneDTO();
                    phoneDTO.setNumber(phone.getNumber());
                    phoneDTO.setCitycode(phone.getCitycode());
                    phoneDTO.setContrycode(phone.getContrycode());
                    return phoneDTO;
                })
                .collect(Collectors.toSet());
    }

    public static User mapToUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhones(mapToPhoneSet(user,userDTO.getPhones()));
        return user;
    }

    public static Set<Phone> mapToPhoneSet(User user,Set<PhoneDTO> phoneDTOs) {
        return phoneDTOs.stream()
                .map(phoneDTO -> {
                    Phone phone = new Phone();
                    phone.setNumber(phoneDTO.getNumber());
                    phone.setCitycode(phoneDTO.getCitycode());
                    phone.setContrycode(phoneDTO.getContrycode());
                    phone.setUser(user);
                    return phone;
                })
                .collect(Collectors.toSet());
    }

    public static  UserCreateDTO mapToUserCreateDTO(User user) {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userCreateDTO.setId(UUID.fromString(user.getId().toString()));
        userCreateDTO.setCreated(dateFormat.format(user.getCreated()));
        userCreateDTO.setModified(user.getModified() != null ? dateFormat.format(user.getModified()) : null);
        userCreateDTO.setLastLogin(dateFormat.format(user.getLastLogin()));
        userCreateDTO.setToken(Utils.generateJwtToken(user.getEmail()));
        userCreateDTO.setIsactive(user.getIsactive() == 1 ? "activo" : "inactivo");
        return userCreateDTO;
    }

}
