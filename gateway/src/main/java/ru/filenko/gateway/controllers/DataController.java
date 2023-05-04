package ru.filenko.gateway.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.filenko.gateway.model.EntityData;
import ru.filenko.gateway.services.DataService;
import ru.filenko.gateway.services.UserService;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DataController {
    private final UserService userService;
    private final DataService dataService;
    @GetMapping(value = "/get-data-entities", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<EntityData> getEntitiesByUser(Principal principal) {
        return userService.getUserByLogin(principal.getName())
                .flatMapMany(user -> dataService.getEntityDataByIdUser(user.getId()));
    }
}
