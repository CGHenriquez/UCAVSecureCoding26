// src/java/src/main/java/com/example/api/controller/TokenController.java
// PASO 8: Insecure Randomness — SecureRandom en lugar de Random para tokens de seguridad

package com.example.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

// CODIGO SEGURO
import java.security.SecureRandom;
import java.util.Base64;

private final SecureRandom secureRandom = new SecureRandom();

@PostMapping("/reset-password")
public ResponseEntity<?> requestReset(@RequestParam String email) {
    byte[] tokenBytes = new byte[32];  // 256 bits de entropia
    secureRandom.nextBytes(tokenBytes);
    String token = Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    // token tiene ~43 caracteres URL-safe, imposible de predecir o forzar bruta
    saveResetToken(email, token);
    return ResponseEntity.ok(Map.of("message", "Reset email sent"));
       
    }
    
    private void saveResetToken(String email, String token) {
        // Persistir token con fecha de expiracion en base de datos
    }
}
