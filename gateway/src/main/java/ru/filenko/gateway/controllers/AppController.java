package ru.filenko.gateway.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.filenko.gateway.model.User;
import ru.filenko.gateway.services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AppController {
    private final UserService userService;

    @GetMapping("/")
    public Mono<String> getStart(Principal principal) {
        return userService.getUserByLogin(principal.getName())
                .flatMap(user -> Mono.just("<a href='/eservice/test'>test method in service</a></br>"
                        + "<a href='/eservice/data'> get " + user.getLogin() + " data </a>"))
                .switchIfEmpty(Mono.just("User not found."));
    }


}
