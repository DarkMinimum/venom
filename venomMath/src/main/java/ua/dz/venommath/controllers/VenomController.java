package ua.dz.venommath.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ua.dz.venommath.service.MathService;

@RestController
public class VenomController {

    @Autowired
    private MathService service;

    //%2b -> +
    @PostMapping("/")
    public double calculateExpression(@RequestBody String ex) {
        Objects.requireNonNull(ex);
        return service.calculateExpression(ex).orElse(0.0);
    }
}
