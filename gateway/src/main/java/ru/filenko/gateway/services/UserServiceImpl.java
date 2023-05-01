package ru.filenko.gateway.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.filenko.gateway.exceptions.UserAlreadyExistsException;
import ru.filenko.gateway.exceptions.UserNotFoundException;
import ru.filenko.gateway.model.User;
import ru.filenko.gateway.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<User> register(String login, String password) {
        return userRepository.findByLogin(login)
                .flatMap(existingUser -> {
                    if (existingUser != null) {
                        return Mono.error(new UserAlreadyExistsException());
                    }
                    User user = new User();
                    user.setLogin(login);
                    user.setPassword(passwordEncoder.encode(password));
                    return userRepository.save(user);
                });
    }

    @Override
    public Mono<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new UserNotFoundException())))
                .onErrorResume(UserNotFoundException.class, Mono::error);
    }
}
