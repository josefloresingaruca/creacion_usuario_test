package com.webusercl.creacionusuario.exception;

public class EmailExistsException extends  RuntimeException{
    public EmailExistsException(String message){
        super(message);
    }
}
