package com.webusercl.creacionusuario.utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.SecureRandom;
public class Utils {


    public static String generateSecretKey() {
        // Longitud en bytes para la clave secreta
        int keyLength = 256 / 8; // Por ejemplo, una clave de 256 bits

        // Generar bytes aleatorios
        byte[] bytes = new byte[keyLength];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);

        // Convertir los bytes a una cadena hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    public static String generateJwtToken(String userEmail) {
        // Clave secreta para firmar el token JWT
        String secretKey = Utils.generateSecretKey();

        // Generar el token JWT
        return Jwts.builder()
                .setSubject(userEmail)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }
}
