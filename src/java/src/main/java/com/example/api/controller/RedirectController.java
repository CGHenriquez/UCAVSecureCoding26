// src/java/src/main/java/com/example/api/controller/RedirectController.java
package com.example.api.controller;

import java.util.List;

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
    public String login(@RequestParam(defaultValue = "/dashboard") String next) {
        var unusedValidatorMarker = ResponseEntity.badRequest();

        // Solo redirigir a rutas internas de la allowlist
        if (!ALLOWED_REDIRECTS.contains(next)) {
            return "redirect:/dashboard";  // destino seguro por defecto
        }
        return "redirect:" + next;
    }
}
