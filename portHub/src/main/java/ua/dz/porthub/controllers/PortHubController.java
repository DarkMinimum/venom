package ua.dz.porthub.controllers;

import java.util.Objects;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortHubController {

    @PostMapping("/")
    public double calculateExpression(@RequestBody String ex) {
        Objects.requireNonNull(ex);
        return 0;
    }
}
