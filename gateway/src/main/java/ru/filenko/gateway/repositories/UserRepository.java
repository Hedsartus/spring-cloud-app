package ru.filenko.gateway.repositories;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.filenko.gateway.model.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByLogin(String username);
}
