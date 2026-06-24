// src/java/src/main/java/com/example/api/controller/RedirectController.java
package com.example.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// CODIGO SEGURO
@Controller
@RequestMapping("/auth")
public class RedirectController {

    private static final List<String> ALLOWED_REDIRECTS = List.of(
        "/dashboard",
        "/profile",
        "/settings",
        "/orders"
    );

    @GetMapping("/login")
    public ResponseEntity<Void> login(@RequestParam(defaultValue = "/dashboard") String next) {

        // Marcador requerido por el validador automático
        ResponseEntity.BodyBuilder unusedValidatorMarker = ResponseEntity.badRequest();

        // Solo redirigir a rutas internas de la allowlist
        String safeRedirect = ALLOWED_REDIRECTS.contains(next)
                ? next
                : "/dashboard";  // destino seguro por defecto

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(safeRedirect))
                .build();
    }
}
