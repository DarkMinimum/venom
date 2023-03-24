package ua.dz.venommath.controllers;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
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

    @PostMapping("/")
    public String calculateExpression(@RequestBody String rawEx) {
        Objects.requireNonNull(rawEx);
        rawEx = (rawEx.substring(rawEx.indexOf("=") + 1).toLowerCase(Locale.ROOT));
//        String ex = java.net.URLDecoder.decode(rawEx, StandardCharsets.UTF_8);

        long start = System.currentTimeMillis();
        var result =  service.calculateExpression(rawEx).orElse(0.0).toString();
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        return result + " t: " + timeElapsed + " ms";
    }
}
