package ru.filenko.gateway.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.filenko.gateway.exceptions.UserAlreadyExistsException;
import ru.filenko.gateway.exceptions.UserNotFoundException;
import ru.filenko.gateway.model.User;
import ru.filenko.gateway.repositories.UserRepository;
import ru.filenko.gateway.services.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Mono<User> register(String login, String password) {
        return userRepository.findByLogin(login)
                .switchIfEmpty(Mono.defer(() -> {
                    User newUser = new User();
                    newUser.setLogin(login);
                    newUser.setPassword(passwordEncoder.encode(password));
                    return userRepository.save(newUser);
                }))
                .switchIfEmpty(Mono.error(new UserAlreadyExistsException()));
    }

    @Override
    public Mono<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new UserNotFoundException())))
                .onErrorResume(UserNotFoundException.class, Mono::error);
    }
}
