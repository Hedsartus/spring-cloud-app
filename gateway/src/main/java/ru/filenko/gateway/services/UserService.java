package ru.filenko.gateway.services;

import reactor.core.publisher.Mono;
import ru.filenko.gateway.exceptions.UserAlreadyExistsException;
import ru.filenko.gateway.model.User;

public interface UserService {
    Mono<User> register(String login, String password) throws UserAlreadyExistsException;
    Mono<User> getUserByLogin(String login);

}
