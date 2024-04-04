package com.webusercl.creacionusuario.repository;

import com.webusercl.creacionusuario.model.Phone;
import com.webusercl.creacionusuario.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface PhoneRepository  extends JpaRepository<Phone,Long> {

}
