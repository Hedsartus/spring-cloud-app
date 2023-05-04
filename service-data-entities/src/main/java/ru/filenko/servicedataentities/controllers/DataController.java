package ru.filenko.servicedataentities.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.filenko.servicedataentities.model.EntityData;
import ru.filenko.servicedataentities.services.DataService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DataController {
    private final DataService dataService;

    @GetMapping("/data")
    public Flux<EntityData> getData(@RequestHeader Map<String, String> headers) {
        return dataService.getDataEntities(headers);
    }
}
