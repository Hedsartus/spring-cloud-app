package ru.filenko.servicedataentities.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.filenko.servicedataentities.model.EntityData;
import ru.filenko.servicedataentities.services.DataService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AppController {
    private final DataService dataService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/data/{id}")
    public Flux<EntityData> getData(@RequestHeader Map<String, String> headers, @PathVariable("id") long id) {
        return dataService.getDataEntities(headers, id);
    }
}
