package ru.filenko.gateway.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.filenko.gateway.model.EntityData;
import ru.filenko.gateway.model.User;
import ru.filenko.gateway.services.DataService;
import ru.filenko.gateway.services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class DataController {
    private final DataService dataService;
    private final UserService userService;

    @GetMapping("/")
    public String getStart(Principal principal) {
        User user = userService.getUserByLogin(principal.getName()).block();

        String stringBuilder = "<a href='/eservice/test'>test method in service</a></br>" +
                "<a href='/eservice/data/" + user.getId() + "'> get " + user.getLogin() +
                " data </a>";

        return stringBuilder;
    }

    @GetMapping(value = "/get-data-entities/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<EntityData> getEntitiesByUser(@PathVariable("id") long id) {
        return dataService.getEntityDataByIdUser(id);
    }
}
