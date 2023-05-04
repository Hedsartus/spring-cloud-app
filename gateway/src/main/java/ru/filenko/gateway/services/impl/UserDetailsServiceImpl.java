package ru.filenko.gateway.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.filenko.gateway.repositories.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {
    private final UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String login) {
        return userRepository.findByLogin(login)
                .map(user -> User.withUsername(user.getLogin())
                        .password(user.getPassword()).authorities("USER")
                        .build())
                .cast(UserDetails.class);
    }
}
