package ru.filenko.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import ru.filenko.gateway.model.EntityData;
import ru.filenko.gateway.model.User;
import ru.filenko.gateway.repositories.EntityRepository;
import ru.filenko.gateway.services.UserService;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class GatewayApplication implements CommandLineRunner {
    private final UserService userService;
    private final EntityRepository entityRepository;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userService.register("user", "user").subscribe(this::addEntities);
        userService.register("david", "david").subscribe(this::addEntities);
    }

    private void addEntities(User user) {
        log.info("User added: {}", user.getLogin());
        Flux.range(0, 20)
                .flatMap(i -> {
                    EntityData entityData = new EntityData();
                    entityData.setIdUser(user.getId());
                    entityData.setTitle("titles" + i);
                    return entityRepository.save(entityData)
                            .doOnError(err -> log.error("Error during entity save: {}", err.getMessage()));
                }).subscribe(entityData -> log.info("Entity added: {}", entityData.getTitle()));
    }
}
