package com.webusercl.creacionusuario.advice;

import com.webusercl.creacionusuario.exception.EmailExistsException;
import com.webusercl.creacionusuario.exception.InvalidEmailFormatException;
import com.webusercl.creacionusuario.exception.InvalidPasswordFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler    {

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<Object> handleEmailExistsException(EmailExistsException ex) {
        ErrorMessage errorMessage = new ErrorMessage("Correo ya registrado");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorMessage);
    }


    @ExceptionHandler(InvalidEmailFormatException.class)
    public ResponseEntity<Object> handleInvalidEmailFormatException(InvalidEmailFormatException ex) {
        ErrorMessage errorMessage = new ErrorMessage("El formato del correo electrónico no es válido");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorMessage);
    }

    @ExceptionHandler(InvalidPasswordFormatException.class)
    public ResponseEntity<Object> handleInvalidPasswordFormatException(InvalidPasswordFormatException ex) {
        ErrorMessage errorMessage = new ErrorMessage("El formato del password no es válido");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorMessage);
    }

    static class ErrorMessage {
        private String mensaje;

        public ErrorMessage(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }

}
