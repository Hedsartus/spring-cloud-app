package ru.filenko.servicedataentities.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppController {
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
