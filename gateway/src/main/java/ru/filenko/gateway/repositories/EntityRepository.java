package ru.filenko.gateway.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.filenko.gateway.model.EntityData;

public interface EntityRepository extends ReactiveCrudRepository<EntityData, Long> {
    Flux<EntityData> getEntityDataByIdUser(Long idUser);
}
