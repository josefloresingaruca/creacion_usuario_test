package com.webusercl.creacionusuario.exception;

public class InvalidPasswordFormatException extends  RuntimeException{
    public InvalidPasswordFormatException(String message){
        super(message);
    }
}
